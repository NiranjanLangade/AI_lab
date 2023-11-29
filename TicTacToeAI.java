package TicTacToe;


import java.util.Scanner;


public class TicTacToeAI {
    private char [][]board;

    public char player = 'X', opponent = 'O';
    public TicTacToeAI()
    {
        board=new char [3][3];

        intializeBoard();

    }
    public void intializeBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=' ';
            }
        }
    }
    public void printBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }

    }
    public boolean isBoardFull()
    {

        for(int row=0;row<3;row++)
        {
            for(int col=0;col<3;col++)
            {
                if(board[row][col]==' ')
                {

                    return false;
                }
            }
        }
        return true;
    }
    public boolean isGameOver() {
        return (isGameWon(player) || isGameWon(opponent) || isBoardFull());
    }

    public boolean isGameWon(char player)
    {
        for(int row=0;row<3;row++)
        {
            if(board[row][0]==player && board[row][1]==player && board[row][2]==player)
            {
                return true;
            }
        }
        for(int col=0;col<3;col++)
        {
            if(board[0][col]==player && board[1][col]==player && board[2][col]==player)
            {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    public int minimax(char currplayer,int depth)
    {
        if (isGameWon(player)) {
            return 1;
        } else if (isGameWon(opponent)) {
            return -1;
        } else if (isBoardFull()) {
            return 0;
        }

        int bestScore = (currplayer== opponent) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]==' ')
                {
                    board[i][j]=currplayer;
                    int score= minimax((currplayer==opponent)?player:opponent,depth+1);
                    board[i][j]=' ';
                    if (currplayer == opponent) {
                        bestScore = Math.min(score, bestScore);
                    } else {
                        bestScore = Math.max(score, bestScore);
                    }

                }
            }
        }



        return bestScore;


    }
    public Move findBestMove()
    {
        int bestScore=Integer.MAX_VALUE;
        Move bestMove=null;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board[i][j]==' ')
                {
                    board[i][j]=opponent;
                    int score=  minimax(player,0);
                    board[i][j]=' ';
                    if(score<bestScore)
                    {
                        bestScore=score;
                        bestMove=new Move(i,j);

                    }
                }
            }

        }
        return bestMove;
    }
    public static class Move{
        int row;
        int col;
        Move(int row,int col)
        {
            this.row=row;
            this.col=col;
        }
    }

    public static void main(String[] args) {
        TicTacToeAI game=new TicTacToeAI();
        Scanner sc=new Scanner(System.in);
        while(!game.isGameOver())
        {
            game.printBoard();
            System.out.print("Enter your move (row column): ");
            int playerRow = sc.nextInt();
            int playerCol = sc.nextInt();
            if (game.board[playerRow][playerCol] == ' ') {
                game.board[playerRow][playerCol] = game.player;
            } else {
                System.out.println("Invalid move. Cell is already occupied. Try again.");
                continue;
            }
            if (game.isGameWon(game.player)) {
                game.printBoard();
                System.out.println("Congratulations! You win!");
                break;
            }
            if(game.isBoardFull())
            {
                game.printBoard();
                System.out.println("It's a draw!");
                break;
            }
            Move move=game.findBestMove();
            game.board[move.row][move.col]= game.opponent;
            System.out.println("Computer's move: " + move.row + " " + move.col);
            if (game.isGameWon(game.opponent)) {
                game.printBoard();
                System.out.println("Computer wins. Better luck next time!");
                break;
            }

        }
        if (!game.isGameWon(game.player) && !game.isGameWon(game.opponent)) {
            game.printBoard();
            System.out.println("It's a draw!");
        }
        sc.close();
    }

}
