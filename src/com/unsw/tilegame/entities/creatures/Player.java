package com.unsw.tilegame.entities.creatures;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.*;
import com.unsw.tilegame.entities.effect.ArrowEffect;
import com.unsw.tilegame.entities.effect.BombEffect;
import com.unsw.tilegame.entities.effect.PotionEffect;
import com.unsw.tilegame.entities.enemy.Enemy;
import com.unsw.tilegame.entities.equipment.Equipment;
import com.unsw.tilegame.entities.statics.Door;
import com.unsw.tilegame.entities.statics.Exit;
import com.unsw.tilegame.entities.statics.Rock;
import com.unsw.tilegame.inventory.Inventory;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Animation;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.tool.Text;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/*
 * @author      Player.java Ni & Yang
 * @version     2.7
 * @since       1.0
 */

public class Player extends Creature implements CreatureInterface, DieBehavior {
	private Animation animDown,animUp,animLeft,animRight;
	private Animation animArrowDown,animArrowUp,animArrowLeft,animArrowRight;
	private Animation animAttackDown,animAttackUp,animAttackLeft,animAttackRight;
	private Animation animAttackArrowDown,animAttackArrowUp,animAttackArrowLeft,animAttackArrowRight;
	private long lastTimeAttack, underAttackTime = 280, attackTimeCalculator = underAttackTime;
	private long lastPushTimer, pushCooldown = 250, pushTimer = pushCooldown;
	private Inventory inventory;
	private int attack = 0;
	public static final int ATTACKORIGINAL = 0;
	public static final int DEFAULT_SPEED = 6;
	public static Equipment currentWeapon = null;
	private static boolean OnFire;
	boolean begin;
	ArrowEffect shot = null;
	public static Vector<ArrowEffect> sshot;
	private static int Treasure;
	BombEffect bombShot = null;
	public static Vector<BombEffect> bbshot;
	private int hit5;
	private static boolean onHover;
	private static boolean onInvincible;
	Vector<PotionEffect> potionList;
	private PlayerMoveAlgorithm howToMove;
	ExecutorService cachedThreadPool;
	public PlayerDieBehavior howToDie;
	/*
	 * Constructor of Player
	 */
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		xMove = 0;
		yMove = 0;
		bounds.setX(16);
		bounds.setY(26);
		bounds.setWidth(32);
		bounds.setHeight(32);
		speed = DEFAULT_SPEED;
		howToMove = new PlayerMoveAlgorithm(this);
		id = 0;
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);

        Treasure = 0;
        hit5 = 0;

        animAttackDown = new Animation(500, Assets.player_attack_down);
        animAttackUp = new Animation(500, Assets.player_attack_up);
        animAttackLeft = new Animation(500, Assets.player_attack_left);
        animAttackRight = new Animation(500, Assets.player_attack_right);
        inventory = new Inventory.InventoryBuilder(handler).build();
        onHover = false;
        onInvincible = false;
        OnFire = false;
        begin = false;
        animAttackArrowDown = new Animation(500, Assets.player_attack_arrow_down);
        animAttackArrowUp = new Animation(500, Assets.player_attack_arrow_up);
        animAttackArrowLeft = new Animation(500, Assets.player_attack_arrow_left);
        animAttackArrowRight = new Animation(500, Assets.player_attack_arrow_right);

        animArrowDown = new Animation(500, Assets.player_archer_down);
        animArrowUp = new Animation(500, Assets.player_archer_up);
        animArrowLeft = new Animation(500, Assets.player_archer_left);
        animArrowRight = new Animation(500, Assets.player_archer_right);
        sshot = new Vector<ArrowEffect>();
        bbshot = new Vector<BombEffect>();
        potionList = new Vector<PotionEffect>();
        cachedThreadPool = Executors.newCachedThreadPool();
        howToDie = new PlayerDieBehavior(this);
	}
	/**
	 * Set player's attack value from classes outside
	 * The attack argument must specify an int.
	 *
	 * @param  		int of attack value
	 */
	public void setAttack(int attack){
		this.attack = attack;
	}
	/**
	 * Get player's attack value from classes outside
	 *
	 * @return  		int of attack value
	 */
	public int getAttack(){
		return this.attack;
	}
	/*
	 * This is the function that links front end with back end
	 * the function will get input from user
	 * and call functions accordingly
	 * @see animation
	 */
	@Override
	public void tick() {
		getInput();
		howToMove.Move(xMove, yMove);
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		handler.getGameCamera().centerOnEntity(this);
		
		checkAttacks();
		pushRock();
		animAttackDown.tick();
		animAttackUp.tick();
		animAttackLeft.tick();
		animAttackRight.tick();
		
		animArrowDown.tick();
		animArrowUp.tick();
		animArrowLeft.tick();
		animArrowRight.tick();
		
		animAttackArrowDown.tick();
		animAttackArrowUp.tick();
		animAttackArrowLeft.tick();
		animAttackArrowRight.tick();
		
		if (animAttackArrowDown.frameIndex()==3||animAttackArrowUp.frameIndex()==3
				||animAttackArrowRight.frameIndex()==3||animAttackArrowLeft.frameIndex()==3)
			shotEnemy();
		enemyMeetBomb();
		inventory.tick();
	}
	/*
	 * Player will die according to howToDie algorithm specify
	 * Game will be over immediately
	 */
    @Override
    public void die() {
        howToDie.die();
    }
	/*
	 * This is a getter method to access treasure outside
	 * @return treasure player own
	 */
	public static int getTreasure(){
		return Treasure;
	}
	/*
	 * This is mainly reacting to keyboad input
	 * if the player is currently on inventory page
	 * then do nothing
	 * if not,
	 * then use W,S,A,D to move
	 */
	private void getInput(){
		if(inventory.isActive())
			return;
		xMove = 0;
		yMove = 0;
		if(handler.getKeyManagerJF().isPressed(KeyCode.W)) {
			yMove = -speed;
		} else if(handler.getKeyManagerJF().isPressed(KeyCode.S)) {
			yMove = speed;
		} else if(handler.getKeyManagerJF().isPressed(KeyCode.A)) {
			xMove = -speed;
		} else if(handler.getKeyManagerJF().isPressed(KeyCode.D)) {
			xMove = speed;
		}	
	}
	/*
	 * This class aims to calculate whether, on x coordinate, player has met the pit
	 * @return whether, on x coordinate, player has met pit
	 */
	public int calculateTileXMove() {
		if (xMove > 0) {
			return (int)(x+xMove+bounds.getX()+bounds.getWidth())/Tile.TILEWIDTH;
		} else if (xMove < 0){
			return (int)(x+xMove+bounds.getX())/Tile.TILEWIDTH;
		}
		return -1;
	}
	/*
	 * This is to calculate whether player has collided with any entities
	 * this includes enemies, static thing (tree, rock...) and equipment
	 * @return whether player has met any entities
	 */
	public boolean calculateXMoveSituation(int tx) {
		return !collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
				!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT);
	}
	/*
	 * This is a function related to pit
	 * @return whether on x coordinate, player hit the pit
	 */
	public boolean calculateXMovePitSituation(int tx) {
		return !collisionWithPitTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT)&&
				!collisionWithPitTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT);
	}
	/*
	 * this is a function related to moving
	 * @param player's x coordinate
	 * @return player's distance with colided object on x coordinate
	 * if it's too far away, @return 0
	 */
	public float calculateXMoveCollisions(int tx) {
		if (xMove > 0) {
			return (float) (tx*Tile.TILEWIDTH-bounds.getX()-bounds.getWidth()-1);
		} else if (xMove < 0){
			return (float) (tx*Tile.TILEWIDTH+Tile.TILEWIDTH-bounds.getX());
		}
		return 0;
	}

	/**
	 * @param tx
	 * @return
	 */
	public float calculateXMovePitCollisions(int tx) {
		if (xMove > 0) {
			return (tx)*Tile.TILEWIDTH+(float)0.15*Tile.TILEWIDTH;
		} else {
			return (tx)*Tile.TILEWIDTH-(float)0.15*Tile.TILEWIDTH;
		}
	}
	public void increaseXMove(){
	    x+=xMove;
    }
    public void increaseyMove(){
	    y+=yMove;
    }
	public int calculateTileYMove() {
		if (yMove < 0) {
			return (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
		} else if (yMove > 0) {
			return (int)(y+yMove+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT;
		}
		return -1;
	}
	public boolean calculateYMoveSituation(int ty) {
		return !collisionWithTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty) &&
				!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty);
	}

	/**
	 * @param ty
	 * @return
	 */
	public float calculateYMoveCollisions(int ty) {
		if (yMove < 0) {
			return (float) (ty*Tile.TILEHEIGHT+Tile.TILEHEIGHT-bounds.getY());
		} else if (yMove > 0){
			return (float) (ty*Tile.TILEHEIGHT-bounds.getY()-bounds.getHeight()-1);
		}
		return -1;
	}

	/**
	 * @param ty
	 * @return
	 */
	public boolean calculateYMovePitSituation(int ty) {
		return !collisionWithPitTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty+Tile.TILEHEIGHT/2)&&
				!collisionWithPitTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty+Tile.TILEHEIGHT/2);
	}

	/**
	 * @param ty
	 * @return
	 */
	public float calculateYMovePitCollisions(int ty) {
		if (yMove > 0) {
			return (ty)*Tile.TILEWIDTH+(float)0.15*Tile.TILEWIDTH;
		} else {
			return (ty)*Tile.TILEWIDTH-(float)0.15*Tile.TILEWIDTH;
		}
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		if (BufferedDrawAttackImage() == null) {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height);
		} else if (currentWeapon != null && BufferedDrawArrowAttackImage() == null) {
			if(currentWeapon.getId() == 3) {
				g.drawImage(getCurrentArrowAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
						(int) (y - handler.getGameCamera().getyOffset()), width, height);
			} else {
				g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
						(int) (y - handler.getGameCamera().getyOffset()), width, height);
			}
		}
		if (currentWeapon != null ) {
			if (currentWeapon.getId() == 2 && BufferedDrawAttackImage()!=null) {
				g.drawImage(BufferedDrawAttackImage(), (int) (x - handler.getGameCamera().getxOffset()), 
						(int) (y - handler.getGameCamera().getyOffset()), width, height);
				Text.drawString(g, "current Weapon is Sword", 800, 100, true, Color.ORANGE, Assets.font28);
			} else if (currentWeapon.getId() == 3 && BufferedDrawArrowAttackImage()!=null){
				g.drawImage(BufferedDrawArrowAttackImage(), (int) (x - handler.getGameCamera().getxOffset()), 
						(int) (y - handler.getGameCamera().getyOffset()), width, height);
				Text.drawString(g, "current Weapon is Arrow", 800, 100, true, Color.YELLOW, Assets.font28);
			} else if (currentWeapon.getId() == 6) {
				OnFire = false;
				Text.drawString(g, "current Equipment is Key", 800, 100, true, Color.YELLOW, Assets.font28);
			} else if (currentWeapon.getId() == 7) {
				OnFire = false;
				Text.drawString(g, "current Equipment is Bomb", 800, 100, true, Color.YELLOW, Assets.font28);
			}
			if (currentWeapon.getCount() == 0) {
				currentWeapon = null;
				OnFire = false;
			}
		}
	}
	@Override
	public Image getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if(xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else  {
			return animDown.getCurrentFrame();
		}
	}
	private Image getCurrentArrowAnimationFrame() {
		if (xMove < 0) {
			return animArrowLeft.getCurrentFrame();
		} else if(xMove > 0) {
			return animArrowRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animArrowUp.getCurrentFrame();
		} else  {
			return animArrowDown.getCurrentFrame();
		}
	}
	private Image BufferedDrawAttackImage() {
		if (OnFire) {
			if (currentWeapon != null) {
				if (currentWeapon.getId() == 2) {
					if (handler.getKeyManagerJF().isPressed(KeyCode.UP)) {
						return animAttackUp.getCurrentFrame();
					}
					if(handler.getKeyManagerJF().isPressed(KeyCode.DOWN)) {
						return animAttackDown.getCurrentFrame();
					}
					if(handler.getKeyManagerJF().isPressed(KeyCode.LEFT)) {
						return animAttackLeft.getCurrentFrame();
					}
					if(handler.getKeyManagerJF().isPressed(KeyCode.RIGHT)) {
						return animAttackRight.getCurrentFrame();
					}
				}
				return getCurrentAnimationFrame();
			}
		}
		return null;
	}
	public void shotEnemy() {
		if (currentWeapon != null) {
			if (currentWeapon.getId() == 3 && sshot.size() < 1) {
				int i = currentWeapon.getCount();
				if (i > 0) {
					if (handler.getKeyManagerJF().isPressed(KeyCode.RIGHT)) {
						shot = new ArrowEffect(handler,x,y,54,8,ArrowEffect.RIGHT);
						sshot.add(shot);
					} else if (handler.getKeyManagerJF().isPressed(KeyCode.LEFT)) {
						shot = new ArrowEffect(handler,x,y,54,8,ArrowEffect.LEFT);
						sshot.add(shot);
					} else if (handler.getKeyManagerJF().isPressed(KeyCode.UP)) { 
						shot = new ArrowEffect(handler,x,y,32,8,ArrowEffect.UP);
						sshot.add(shot);
					} else if (handler.getKeyManagerJF().isPressed(KeyCode.DOWN)) {
						shot = new ArrowEffect(handler,x,y,32,8,ArrowEffect.DOWN);
						sshot.add(shot);
					}
					if (shot != null) {
						cachedThreadPool.execute(shot);
					}
				}
			}
		}
	}
	private Image BufferedDrawArrowAttackImage() {
		if (OnFire) {
			if (currentWeapon != null) {
				if (handler.getKeyManagerJF().isPressed(KeyCode.UP)) {
					return animAttackArrowUp.getCurrentFrame();
				}
				if(handler.getKeyManagerJF().isPressed(KeyCode.DOWN)) {
					return animAttackArrowDown.getCurrentFrame();
				}
				if(handler.getKeyManagerJF().isPressed(KeyCode.LEFT)) {
					return animAttackArrowLeft.getCurrentFrame();
				}
				if(handler.getKeyManagerJF().isPressed(KeyCode.RIGHT)) {
					return animAttackArrowRight.getCurrentFrame();
				}
				return getCurrentArrowAnimationFrame();
			}
		}
		return null;
	}
	public void setOnHover(){
	    onHover = false;
    }
    public void setOnInvincible(){
	    onInvincible = false;
    }

    public void setCurrentWeapon(){
	    currentWeapon = null;
    }

    public Inventory getInventory() {
		return inventory;
	}
	public static Equipment getCurrentWeapon() {
		return currentWeapon;
	}

	/**
	 * @param currentWeapon
	 */
	public static void setCurrentWeapon(Equipment currentWeapon) {
		Player.currentWeapon = currentWeapon;
	}
	public boolean hasWeapon() {
		if (currentWeapon!= null && currentWeapon.getId() != 6) {
			return true;
		}
		return false;
	}
	public void enemyMeetBomb() {
		if (currentWeapon != null) {
			if (currentWeapon.getId() == 7 && bbshot.size()<1) {
				int i = currentWeapon.getCount();
				if (i > 0) {
					if (handler.getKeyManagerJF().isPressed(KeyCode.RIGHT)) {
						bombShot = new BombEffect(handler,x,y,ArrowEffect.RIGHT);
						bbshot.add(bombShot);
					} else if (handler.getKeyManagerJF().isPressed(KeyCode.LEFT)) {
						bombShot = new BombEffect(handler,x,y,ArrowEffect.LEFT);
						bbshot.add(bombShot);
					} else if (handler.getKeyManagerJF().isPressed(KeyCode.UP)) { 
						bombShot = new BombEffect(handler,x,y,ArrowEffect.UP);
						bbshot.add(bombShot);
					} else if (handler.getKeyManagerJF().isPressed(KeyCode.DOWN)) {
						bombShot = new BombEffect(handler,x,y,ArrowEffect.DOWN);
						bbshot.add(bombShot);
					}
					if (bombShot != null) {
						cachedThreadPool.execute(bombShot);
					}
				}
			}
		}
	}

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return
	 */
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		if(handler.getEntityManager() != null) {
			if (handler.getEntities() != null) {
				for(Entity e : handler.getEntities()) {
					if (e.equals(this))
						continue;
					if (e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
							getCollisionBounds(xOffset, yOffset).getBoundsInLocal())) {
						if (e instanceof Enemy) {
							if (!onInvincible) {
								if (!handler.getPlayer().hasWeapon()) {
									try {
										Thread.sleep(1000);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									handler.getPlayer().die();
								}
							} else if (onInvincible){
								((Enemy)e).die();
							}
						} else if (e instanceof Door) {
							((Door)e).move();
							if (((Door)e).open) {
								return false;
							}
						} else if (e.id == Exit.EXIT_ID) {
							Game.isWin = true;
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param r1
	 * @param r2
	 * @return
	 */
	public boolean checkEntitiesPush(Rectangle r1, Rectangle r2) {
		int arSize = 20;
		r2.setWidth(arSize);
		r2.setHeight(arSize);
		double rUpDown = r1.getX()+r1.getWidth()/2;
		double rLeftRight = r1.getY()+r1.getHeight()/2;
		if (handler.getKeyManagerJF().isPressed(KeyCode.W)) {
			r2.setX(rUpDown-arSize/2);
			r2.setY(r1.getY()-arSize);
		}else if (handler.getKeyManagerJF().isPressed(KeyCode.S)) {
			r2.setX(rUpDown-arSize/2);
			r2.setY(r1.getY()+arSize);
		}else if (handler.getKeyManagerJF().isPressed(KeyCode.A)) {
			r2.setX(r1.getX()-arSize);;
			r2.setY(rLeftRight-arSize/2);
		}else if (handler.getKeyManagerJF().isPressed(KeyCode.D)) {
			r2.setX(r1.getX()+r1.getWidth());
			r2.setY(rLeftRight-arSize/2);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * @param r1
	 * @param r2
	 * @return
	 */
	public boolean checkEntitiesAttack(Rectangle r1, Rectangle r2) {
		int arSize = 20;
		r2.setWidth(arSize);
		r2.setHeight(arSize);
		double rUpDown = r1.getX()+r1.getWidth()/2;
		double rLeftRight = r1.getY()+r1.getHeight()/2;
		if (handler.getKeyManagerJF().isPressed(KeyCode.UP)) {
			r2.setX(rUpDown - arSize/2);
			r2.setY(r1.getY()-arSize);
		}else if (handler.getKeyManagerJF().isPressed(KeyCode.DOWN)) {
			r2.setX(rUpDown - arSize/2);
			r2.setY(r1.getY()+arSize);
		}else if (handler.getKeyManagerJF().isPressed(KeyCode.LEFT)) {
			r2.setX(r1.getX()-arSize);;
			r2.setY(rLeftRight-arSize/2);
		}else if (handler.getKeyManagerJF().isPressed(KeyCode.RIGHT)) {
			r2.setX(r1.getX()+r1.getWidth());
			r2.setY(rLeftRight-arSize/2);
		} else {
			return false;
		}
		return true;
	}
	public void checkAttacks() {
		if (currentWeapon != null) {
			if (currentWeapon.getId() == 2) {
				attackTimeCalculator += System.currentTimeMillis()-lastTimeAttack;
				lastTimeAttack = System.currentTimeMillis();
				if (attackTimeCalculator < underAttackTime)
					return;
				if (inventory.isActive())
					return;
				Rectangle cb = getCollisionBounds(0,0);
				Rectangle attackR = new Rectangle();
				if (checkEntitiesAttack(cb,attackR)) {
					attackTimeCalculator = 0;
					for(Entity e : handler.getEntities()) {
						if (e.equals(this))
							continue;
						if(e.getCollisionBounds(0, 0).getBoundsInParent().intersects(
								attackR.getBoundsInParent())) {
							if (e instanceof Enemy) {
								((Enemy)e).hurt(attack);
								hit5++;
							}
							if(hit5 == 5) {
								inventory.removeEquipment(currentWeapon);
								currentWeapon = null;
								Player.OnFire = false;
								hit5 = 0;
							}
							return;
						}	
					}
				} else {
					return;
				}
			}
		}
	}
	public void pushRock() {
		pushTimer += System.currentTimeMillis()-lastPushTimer;
		lastPushTimer = System.currentTimeMillis();
		if (pushTimer < pushCooldown)
			return;
		if (inventory.isActive())
			return;
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle push = new Rectangle();
		if (checkEntitiesPush(cb,push)) {
			pushTimer = 0;
			if (handler.getEntityManager()!= null) {
				if (handler.getEntities() != null) {
					for(Entity e : handler.getEntities()) {
						if (e.equals(this))
							continue;
						if(e.getCollisionBounds(0, 0).getBoundsInParent().intersects(
								push.getBoundsInParent())) {
							if (e instanceof Rock) {
								Rock.xM = xMove;
								Rock.yM = yMove;
								((Rock)e).move();
							}
							return;
						}	
					}
				}
			}
		} else {
			return;
		}
	}

	/**
	 * @param g
	 */
	public void postRender(GraphicsContext g) {
		if (handler.getPlayer() != null) {	
			inventory.render(g);
		}
	}

	/**
	 * @param invincible
	 */
	public static void setOnInvincible(boolean invincible){
		onInvincible = invincible;
	}

	/**
	 * @return
	 */
	public static boolean getInvincible(){
		return onInvincible;
	}
	public static void setTreasure(int treasure){
		Treasure = treasure;
	}

	public boolean isHover() {
		return Player.onHover;
	}
	public static void setOnHover(boolean hover){
		onHover = hover;
	}
	/*
	 * This is the function that allows other classes to set on fire status outside
	 * @param whether player is on fire or npt
	 */
	public static void setOnFire(boolean fire){
		OnFire = fire;
	}
    @Override
    public CreatureAlgorithm getCreatureAlgorithm() {
        return howToMove;
    }
}