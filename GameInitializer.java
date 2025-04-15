public class GameInitializer {

    public GameContext initializeGame(Player player, GameContext context) {

        
        Room forestEntrance = new Room("森林入口", "你站在茂密森林的邊緣，前方隱約可見一座廢棄的建築。");
        Room abandonedTemple = new Room("廢棄神殿", "破碎的石牆上布滿藤蔓，空氣中瀰漫著腐朽的氣息。");
      

      
        Monster goblin = new Monster("哥布林", 30, 8);
        Monster skeleton = new Monster("骷髏戰士", 50, 12);



        context.addMonster(goblin);
        context.addMonster(skeleton);
        


        forestEntrance.setMonster(goblin);
        abandonedTemple.setMonster(skeleton);


        forestEntrance.setExit("north", abandonedTemple);
        abandonedTemple.setExit("south", forestEntrance);


       
        forestEntrance.setHasPotion(true);
      
        context.setCurrentRoom(forestEntrance);

    
        player.addSkill(new FireballSkill());
        
        return context;
    }
}