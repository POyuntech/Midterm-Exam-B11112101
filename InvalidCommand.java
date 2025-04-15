class InvalidCommand implements Command {
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(GameContext context, Player player) {
        System.out.println("🤔 無效指令" + (message == null || message.isEmpty() ? "。" : "：" + message)); // 修正 message 可能為 null 的情況
        System.out.println("請嘗試：move <方向>, look, attack, use potion, skill <技能名>, exit");
    }
}