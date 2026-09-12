<h1>"Space Invaders" Like Game</h1>

A WIP Space invaders like game made in Java.

<h1>How It's Made</h1>

Built with Java Swing/AWT, using a JPanel (GamePanel) as the core game surface. A few things stand out in the implementation:

<Ul>
  <li>
Game loop: Runs on its own Thread, using a delta-time accumulator (nanoTime() comparisons against a target of 30 FPS) to decide when to update game state and trigger a repaint() — a classic fixed-timestep loop pattern.
  </li>
  <li>
Rendering: Custom paintComponent() override draws a scrolling starfield background (two overlapping image draws offset by a backgroundScrollOffset counter, creating a seamless vertical scroll loop), then draws the player, enemies, and projectiles on top via Graphics2D.
    </li>
<li>Entities: Player, Enemy, and Projectile are separate classes, each with their own update()/draw() methods, tracked in ArrayLists that the panel iterates each frame.</li>
<li>Enemy spawning: A simple frame counter spawns a new Enemy every 60 ticks (~2 seconds at 30 FPS).</li>
<li>Collision detection: Axis-aligned bounding box (AABB) checks between each projectile and enemy's position/hitbox coordinates; a hit flips both objects' alive flags, and dead objects get filtered out of their lists during the next update().</li>
<li>Input: A dedicated PlayerInput class (implementing a key listener) is passed into Player and registered on the panel, decoupling input handling from the entity logic.</li>
<li>Scaling: Tiles are defined at a base 16px and scaled 3x for a 576×864 window, keeping pixel-art proportions clean on modern screens.</li>
</Ul>
