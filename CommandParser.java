public class CommandParser {

    public Command parse(String inputLine) {
        if (inputLine == null || inputLine.trim().isEmpty()) {
            return new InvalidCommand("輸入為空");
        }

        String[] parts = inputLine.trim().split("\\s+", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
            case "move":
            case "go":
                if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    return new MoveCommand(parts[1].trim());
                } else {
                    return new InvalidCommand("你要往哪個方向移動？ (例如: move north)");
                }
            case "attack":
            case "fight":
                return new AttackCommand();
            case "look":
            case "l":
                 return new LookCommand();
            case "use":
                if (parts.length > 1 && parts[1].trim().equalsIgnoreCase("potion")) {
                    return new UsePotionCommand();
                } else {
                    return new InvalidCommand("你要使用什麼？ (目前僅支援: use potion)");
                }
            case "skill":
            case "cast":
                 if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                    return new SkillCommand(parts[1].trim());
                } else {
                    return new InvalidCommand("你要施放哪個技能？ (例如: skill 火球術)");
                }
            case "quit":
            case "exit":
                 return new QuitCommand();
            default:
                return new InvalidCommand("無法識別的指令 '" + commandWord + "'");
        }
    }
}