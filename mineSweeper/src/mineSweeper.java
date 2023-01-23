import java.util.Random;
import java.util.Scanner;
 class MineSweeper {

    boolean game =true;
    String[][]mineMap;
    String[][]gameBoard;
    int rowNumber;
    int colNumber;
    int mineSize;
    int Size;
    Random rand = new Random();
    Scanner sc = new Scanner (System.in);
    MineSweeper(){
        System.out.println("MayınTarlası Oyununa Hoşgeldiniz!!");
        System.out.println("Lütfen oynamak istediğiniz Mayın Tarlası Boyutunu Giriniz\n");
        System.out.print("Satır Sayısı: ");
        int row = sc.nextInt();
        System.out.print("Sütun Sayısı: ");
        int column = sc.nextInt();
        this.colNumber= column;
        this.rowNumber = row;
        this.mineMap = new String [row][column];
        this.gameBoard = new String [row][column];
        this.Size = (row * column);
        this.mineSize = Size/4;

    }
    private boolean isInside(int row, int col) {
        return row >= 0 && row < mineMap.length && col >= 0 && col < mineMap[0].length;
    }

     boolean isMine(int row, int col) {
         return mineMap[row][col].equals("*");
     }

    void run(){
        int Win = 0;
        prepareGame();
        while(game){
            System.out.println("==================");
            printBoard(gameBoard);
            System.out.print("Satır: ");
            int row = sc.nextInt();
            System.out.print("Sütun: ");
            int column = sc.nextInt();
            if (!isInside(row,column)){
                System.out.println("Belirlediğiniz sınırların dışına çıktınız. Lütfen geçerli bir değer giriniz.");
                continue;
            }
            if(!mineMap[row][column].equals("*")){
                isCheckBoard(row,column);
                Win++;
                if(Win == (Size-(Size/4))){
                    System.out.println("Tebrikler! Bütün mayınların yerini buldunuz.");
                    break;
                }
            }else {
                System.out.println("Mayına bastınız! Oyun Bitti!!");
                game = false;
            }


        }

    }
    /*boolean isWin(){
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                for(String [] r: gameBoard){
                    for(String c: r){
                        if(c.equals("*")){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }*/

    void prepareGame(){
        int count =0,randRow,randCol;
        for (int i = 0; i < mineMap.length;i++){
            for (int j = 0; j< mineMap[i].length;j++){
                mineMap[i][j] = "-";
                gameBoard[i][j] = "-";
            }
        }

        while(count < mineSize){
            randRow = rand.nextInt(this.rowNumber);
            randCol = rand.nextInt(this.colNumber);
            if(mineMap[randRow][randCol].equals("-")){

                    mineMap[randRow][randCol] = "*";
                    count++;

            }
        }


    }

    void printBoard(String[][] arr){

        for (int i = 0; i< arr.length;i++){
            for(int j = 0 ; j < arr[i].length; j++){
                System.out.print((arr[i][j]) + " ");
            }
            System.out.println();
        }
    }

    void isCheckBoard(int row,int col) {
        int mineCount = 0;
        for (int i = row-1; i <= row+1; i++) {
            for (int j = col-1; j <= col+1; j++) {
                if (isInside(i, j) && isMine(i, j)) {
                    mineCount++;
                    gameBoard[row][col] = Integer.toString(mineCount);

                }else if(isInside(i,j) && ! isMine(i,j)){
                    gameBoard[row][col] = Integer.toString(mineCount);
                }

            }
        }

        if (mineMap[row][col].equals("-")) {
            if ((col < mineMap[0].length-1) && (row < mineMap.length-1) && (mineMap[row+1][col+1].equals(""))){
                int currentVal = Integer. parseInt(gameBoard[row][col]);
                currentVal++;
                gameBoard[row][col] = Integer.toString(currentVal);
            }
            if((col > 0) && (row > 0) && (mineMap[row-1][col-1].equals(""))){
                int currentVal = Integer. parseInt(gameBoard[row][col]);
                currentVal++;
                gameBoard[row][col] = Integer.toString(currentVal);
            }
            if((col > 0) && (row < mineMap.length-1) && (mineMap[row+1][col-1].equals(""))){
                int currentVal = Integer. parseInt(gameBoard[row][col]);
                currentVal++;
                gameBoard[row][col] = Integer.toString(currentVal);
            }
            if((col < mineMap[0].length-1) && (row > 0) && (mineMap[row-1][col+1].equals(""))){
                int currentVal = Integer. parseInt(gameBoard[row][col]);
                currentVal++;
                gameBoard[row][col] = Integer.toString(currentVal);
            }
        }

    }
 }





