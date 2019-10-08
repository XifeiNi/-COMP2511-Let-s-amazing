## Let's Amazing

***Let's Amazing is an interactive Java game that has the following user stories***

***You can view a demo here: [Demo](https://www.youtube.com/watch?v=ktgKHcUlwtc&t=1s)***

# Build

`-Dfile.encoding=UTF-8 -classpath /path/to/JavaFxApplication com.unsw.tilegame.Main`


# Epic Story 1
As a user, I would like to move on the maze and encounter various objects.
## ID: US1
- Name: Move Using Keyboard
- User Story Description
- As a user, I would like to move so that I can navigate the map.
- Acceptance Criteria:
- An obstacle is defined as a boulder,  a wall, or a closed door.
- When the user presses up, they will be able to move into the adjacent square on the top unless there’s an obstacle preventing them to do so. 
- When the user presses down, they will be able to move into the adjacent square under the user unless there’s an obstacle preventing them to do so. 
- When the user presses right, they will be able to move into the adjacent square on the right of unless there’s an obstacle preventing them to do so. 
- When the user presses up, they will be able to move into the adjacent square on the top unless there’s an obstacle preventing them to do so. 
- The door is not defined as an obstacle when after the user has opened it, with the image as .
- Estimate:3
- Priority: High

## ID: US2
- Name: Arriving at the Exit
- User Story Description:
- As a user, I would like to enter to next round if I finally rich the exit so that I can keep exploring a lot of fun in this game.
- Acceptance Criteria:
- When the user moves to the exit, the user will be automatically placed to the entrance of the map in the next round.
- Exactly 1.49 seconds delay after the user arrives at the exit, they will be automatically directed to the next round.
- Estimate:3
- Priority:High

## ID: US3
- Name: Encountering Obstacles
- User Story Description:
- As a user, I would like to encounter some obstacles while playing the games so that I can experience a sense of challenge.
- Acceptance Criteria:
- When the user encounters a boulder, they will be able to move one boulder to the adjacent empty square.
- The user cannot get across a pit, a boulder, or a closed door, without doing anything
- The user will be able to destroy a boulderusing a bomb(the way to use a bomb refers to US4)
- The user will die immediately if they fall into a pit.
- The user will be able to fill the pit by pushing a boulder into it. The pit and the boulder will disappear afterwards
- The user needs to find a keyto open the closed door. After the user has a key and equip with it, they can open the door by moving through it, and the door will remain open afterwards.
- Within any circumstances, there is no way to cross a wall.
- Estimate: 5
- Priority:High

## ID: US4
- Name: Encountering Enemies
- User Story Description:
- As a user, I would like to encounter some enemies in this game so I can have fun fighting against them.
- Acceptance Criteria:
- The user will die if they directly collide with the hunter, the strategist, the hound, and the coward.
- The hunter constantly moves towards the player and will stop if they can’t get any closer.
- The strategistmoves towards a square that the player is likely to move next.
- The hound assists the hunter by positioning themselves so that the player is between it and the hunter.
- The coward behaves like a hunter when they run close to the player, but they will run away when the player gets close.
- Estimate: 7
- Priority: High

## ID: US5
- Name:Encountering Floor Switch
- User Story Description
- As a user, I would like to get across a floor switch and trigger them so that I can complete my dungeon.
- Acceptance Criteria:
- A floor switch is like an empty square that user can move across.
- When there’s a boulder on it, the switch it triggered, otherwise untriggered. 
- The triggering of the switch relates the completion of the dungeon. (see Epic 3)
- Estimate: 1
- Priority: High

# Epic Story 2
- As a user, I would like to use my equipment in my inventory
## ID: US6
- Name: Equipping and Unequiping Items from the inventory.
- User Story Description:
- As a user, I would like a convenient way to carry my equipment so that I am ready to fight against my enemies.
- Acceptance Criteria:
- The player needs to press “E” to open inventory, and press enter on the selected term to carry them. Press enter on an empty box will result in the player to carry nothing.
- When the player carries arrow ,sword, and bomb ,they have to press enter to make use of it.
- When the player wants to equip with an arrow, they must enter the number they want to equip. The upper limit of the number is the number of arrows the player owns.
- When the player presses f, all the equipment they carry will be put back into their inventory.
- A player can only carry one weapon. A player cannot carry any potions, and treasures with them: those things can only stay in their inventory.
- All the items the player acquired during the previous rounds will still be available to use at subsequent rounds. 
- Estimate: 5
- Priority: High

## ID: US7
- Name: Fighting Enemies
- User Story Description:
- As a user, I would like to fight against my enemies so that they don’t get in our ways.
- Acceptance Criteria:
- Any enemies will be destroyed by either a sword , or arrow, or a bomb.
- The player should drop a bomb by pressing d on their key board
- The light up a bomb, the player should press l. 
- Upon explosion of the bomb, the animation should be like , ,,, and the bomb will explode within 2 seconds.
- Upon explosion of the bomb, any boulders, and enemies that are in the left, right, above, and below the bomb will be destroyed. If the player is in those squares, they will die too.
- A sword or an arrow will be automatically used to fight if the player is carrying them and they are in adjacent squares.
- A sword will disappear when they have been used 5 times.
- An arrow will be disappeared when they have been used one time.
- Estimate: 6
- Priority: High

## ID: US8
- Name: Getting into the door
- User Story Description: 
- I would like to get through a closed door with my key so that I can pass this round/get to the exit.
- Acceptance Criteria:
- The player will need to collect the key first by moving through the square with a key.
- The player needs to press E to access their inventory, and press Enter at the key item to equip with it.
- After the above is done, the player may walk through the closed door.
- After the player moves through the door, it will remain open, and the door will look like this.
- Estimate: 1
- Priority: High

## ID: US9
- Name: Using Potions
- User Story Description:
- As a player, I would like to use some supernatural potion to get me through the enemies and obstacles. 
- Acceptance Criteria:
- The hover potion will enable the player to hover over all the physical obstacles, but not their enemies.
- The hover potionwill last until the player has been destroyed by their enemies, or a bomb.
- The invisible potion will enable the player to become invisible to all the bombs and enemies.
- Upon collision with enemies while invisible potion is in effect, they will be destroyed immediately.
- All enemies are trying to run away from the player if they are in the invisible potion.
- The invisible potion will only last for 1 minute.
- Estimate: 2
- Priority: High

# Epic Story 3:
As a user, I would like to get a various experience with this game by experiencing various difficulties.
## ID: US10 
- User Story Description:
- As a user, I would like to experience various mazes so that I don’t get bored.
- Acceptance Criteria:
- Map refers to a maze with only walls. E.g.

- Create at least 10 different maps.
- When the user gets to next round, the map randomly updates another map that is different from the previous one.
- Estimate:1
- Priority: Medium -> Low

## ID: US11
- Name: Increasing Complexities
- User Story Description:
- As a user, I would like to encounter more objects on my way to exit so that I can have more fun.
- Acceptance Criteria:
- At round one, the only obstacle on the road is the wall. 
- The physical obstacle, aka,  will appear at round 2,along with other objects that have appeared previously.
- The treasure will appear at round 3,along with other objects that have appeared previously.
- The closed door and the key will appear at round 5, along with other objects that have appeared previously.
- The pit will appear at round 4, along with other objects that have appeared previously.
- The floor switch will appear at round 6, along with other objects that have appeared previously.
- The bombs, sword, and arrow will appear at round 7, along with other objects that have appeared previously.
- All the enemies will appear at round 8, along with other objects that have appeared previously.
- All potions will appear at round 9, along with other objects that have appeared previously.
- Estimate: 3
- Priority: Medium

## ID: US12
- Name: Diverse ways of passing a round
- User Story Description:
- As a user, I would like more than one way to pass a round so that I don’t have to rely on a single way.
- Acceptance Criteria:
- If there’s an exit, then the user must arrive at the exit to complete the dungeon. 
- If there’s no exit, then a dungeon is completed as one or more of the following:
- Destroying all enemies.
- Having a boulder on all floor switches.
- Collecting all the treasure.
- There will always be an exit in the non-user-designing mode.
- Estimate:3
- Priority: medium

# Epic Story 4
- As a user, I would like to design my own game so I can customize my preference
## ID: US13
- Name: Choosing Conditions of Victory
- User Story Description:
- As a user, I would like to customize conditions of victory so that I can win in the conditions I set.
- Acceptance Criteria:
- The user will be able to combine/choose from ways of passing a round from US12.
- Test case 1: the user combine with destroying all the enemies/ and arriving at the exit as the condition of victory: the user must destroy all the enemies and arriving at the exit to win this round
- Test case 2: the user set collecting all the treasure as the condition of victory: the user must have collected all the gold to pass this round.
- Estimate:3
- Priority: medium

## ID: US14
- Name: Customizing number of Objects
- User Story Description:
- As a user, I would like to choose how many of each type of objects I will meet so that I can have a more personalized experience in game
- Acceptance Criteria:
- This setting appears after user has set the condition of victory (US13)
- The user will be able to customize how many treasures will there be in the game
- The user will be able to customize how many enemies of each type will be in the game
- The user won’t be able to customize the map, potion, and door.
