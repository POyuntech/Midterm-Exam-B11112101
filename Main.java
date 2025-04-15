import java.util.Scanner; // 需要 import

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8"); // 嘗試指定 UTF-8
        CommandParser parser = new CommandParser();
        GameInitializer initializer = new GameInitializer();

        Player player = new Player("勇者", 100, 15);
        GameContext context = new GameContext();

        context = initializer.initializeGame(player, context);

        System.out.println("\n👾 [ 遊戲開始！歡迎 " + player.getName() + "！ ]");
        System.out.print(context.getCurrentRoom().getEntryInfo());

        while (player.isAlive() && !context.isGameOver()) {
            System.out.print("\n> ");
            String inputLine = scanner.nextLine();

            Command command = parser.parse(inputLine);

           
            command.execute(context, player);

          
            context.checkWinCondition();
        }

        System.out.println("\n===============================");
        if (!player.isAlive()) {
            System.out.println("💀 你失敗了...再接再厲！");
            player.printCombatStats(null); 
            System.out.println("🏆 *** 恭喜！你擊敗了所有怪物！ ***");
            player.printCombatStats(null);
        } else { 
            System.out.println("👋 感謝遊玩！再會，" + player.getName() + "！");
        }
         System.out.println("===============================");

        scanner.close();
    }
}