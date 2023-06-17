# AFK Playtime

Adds a playtime scoreboard criteria (that accounts for players being AFK in Essential Commands)

Downloads are in the releases section

## Usage:
1. Create a new scoreboard: `scoreboard objectives add playtime playtimeperms:playtime`
2. Enjoy.

Stored in minutes:
A player with a score of 2 will have been playing for 2 minutes.

To execute something once they reach a certain point (in this case 5 minutes), use:
`execute as @a if score @s playtime matches 5 run ...`

Eg. Give a new role and tell them good job once they reach a certain point.
### Command blocks:

`execute as @a if score @s playtime matches 5 run <give new group command>`

`execute as @a if score @s playtime matches 5 run tellraw @s "good job amigo"`

### Datapacks:
mynamespace:tick.mcfunction
```
execute as @a if score @s playtime matches 5 run function mynamespace:myfunc
```

mynamespace:myfunc.mcfunction
```
<give new group command>
[whatever other stuff you want goes here, particles, removing old groups, etc.)
tellraw @s "good job amigo"
```



## Requirements

### Minecraft 1.19.x
### Requires Essential Commands for Fabric
### Requires Fabric API

Only required on the server, not the client.
