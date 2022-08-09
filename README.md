# disablemessagedeletion

This mod allows you to disable the server's ability to retroactively remove messages on the client.

By default, the minimum amount of seconds to pass before a message can be deleted is 10 seconds. By
installing [modmenu](https://www.curseforge.com/minecraft/mc-mods/modmenu)
and [cloth config](https://www.curseforge.com/minecraft/mc-mods/cloth-config), you can change the delay, or completely
disable message deletion by setting it to `-1`.

Here, the minimum delay is set to 5 seconds. If a deletion attempt is made before that, the message in question gets a
purple indicator. Only after the delay has passed, the server can actually delete the message.

![Deletion attempts](https://i.imgur.com/e74fPn6.gif)

## License

This project is licensed under the [MIT license](LICENSE).
