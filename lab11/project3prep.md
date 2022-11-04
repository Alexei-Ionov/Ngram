# Project 3 Prep

**For tessellating pluses, one of the hardest parts is figuring out where to place each plus/how to easily place plus on screen in an algorithmic way.
After looking at your own implementation, consider the `HexWorld` implementation provided near the end of the lab. Note that `HexWorld` was the lab assignment from a previous semester (in which students drew hexagons instead of pluses). 
How did your implementation differ from the given one (aside from obviously hexagons versus pluses) ? What lessons can be learned from it?**

Answer: 

Although I didn't implement the tesselation portion of the lab (as it was optional), I realized that my approach, had I done it, would have 
varied drastically since it would have been breadth first search oriented. In this case, I would have a starting plus and I would add pluses in a 
bfs manner which would follow the pattern demonstrated/shown in the drawings in the lab spec. However, in the HexWorld implementation, the 
algorithm used was much simpler: it approached the tesselation in terms of columns which drastically lowered the complexity of the problem. 
A lesson learned: be patient and try to find the most simple approach before diving in. 


-----

**Can you think of an analogy between the process of tessellating pluses and randomly generating a world using rooms and hallways?
What is the plus and what is the tesselation on the Project 3 side?**

Answer: 

In terms of the Project 3 side, the act of adding pluses would be analogous to adding rooms in our randomly generated world. 
On the other hand, tesselating this world would be analagous to creating hallways and adding other rooms in a manner such that the laws of the game
can function smoothly (e.g. you wouldn't have a room inside of another room, rather maybe two rooms connected by a hallway).

-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating pluses.**

Answer:

I would add a FillWorldWithNothing method which would initially set everything in the world to a nothing tile. Afterword, I would create a method 
which would generate a room with an arbitrary size at a given position.

-----
**What distinguishes a hallway from a room? How are they similar?**

Answer:

In my interpretation, a hallway differs from a room in that it has a width of 1 tile and at one point it connects two different rooms. 
They are similar in that they are both made of the same tile potentially, or at least they are both NOT nothing or wall tiles.c