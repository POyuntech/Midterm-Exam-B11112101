class MoveCommand implements Command {
    private String direction;

    public MoveCommand(String direction) {
        this.direction = direction.toLowerCase();
    }

    @Override
    public void execute(GameContext context, Player player) {
        Room currentRoom = context.getCurrentRoom();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null) {
            context.setCurrentRoom(nextRoom);
            System.out.print(nextRoom.getEntryInfo());
        } else {
            System.out.println("你不能往那個方向走。");
        }
    }
}