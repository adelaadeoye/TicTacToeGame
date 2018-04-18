
/*
 * Copyright (c) 2018. This app is work of great ideas and reference to other online resources. If you will have to make use of any part of the content please go on but don't hesitate to reference me @github.com/adelaadeoye
 */

package com.example.dell.tictoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * this is the computer play aspect of the game
 * the human player is expected to play first then the computer
 * due to little knowledge about loop, issues like computer makes all its moves before the
 * human player arises
 */
@SuppressWarnings("ALL")
public class singleVsCom5X5 extends AppCompatActivity implements View.OnClickListener {
    private int jointMoves = 0;
    private final boolean[][] boardStatus = new boolean[5][5];
    private final Button[][] comPlay = new Button[5][5];
    private int win = 0;
    private int scoreOne = 0;
    private int scoreTwo = 0;
    private int gamePlayed=0;
    private Button restart;
    private TextView level;
    private TextView scorePlayer;
    private TextView scoreCom;
    private TextView playerOneName;
    private TextView gamePlay;
    private TextView gameDraw;
    private String WinnnerName;
    private String playerOneMoveType;
    private String comMoveType;
    private Random mRand;
    private final Context c=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_vs_com5_x5);
        mRand = new Random();

        //Score Board
        scorePlayer = findViewById(R.id.scorePlayer);
        scoreCom = findViewById(R.id.scoreCom);
        gamePlay=findViewById(R.id.gamePlayed);
        gameDraw=findViewById(R.id.gameDraw);
        //get all the play buttons id's
        Button player1 = findViewById(R.id.player1);

        //player name
        playerOneName = findViewById(R.id.playerOneName);

        //Difficulty Level
        level = findViewById(R.id.difficultyLevel);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String moveType = (String) bundle.get("moveType");
//            textView.setText(moveType);
            assert moveType != null;
            if ("X".matches(moveType)) {
                playerOneMoveType = "X";
                comMoveType = "O";
            } else if ("O".matches(moveType)) {
                playerOneMoveType = "O";
                comMoveType = "X";
            }
            String playerOne = (String) bundle.get("player1");
            player1.setText(playerOne);
            if (player1.getText().toString().matches("")) {
                player1.setText("Player One "+playerOneMoveType+"'s Move");
                playerOneName.setText("Player One");
            } else {
                player1.setText(playerOne + " "+playerOneMoveType+"'s Move");
                playerOneName.setText(playerOne);
            }
            //Level
            String levelType = (String) bundle.get("difficultyLevel");
            level.setText(levelType);


        }


        //restart
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        //First Row
        comPlay[0][0] = findViewById(R.id.btnPlay1);
        comPlay[0][1] = findViewById(R.id.btnPlay2);
        comPlay[0][2] = findViewById(R.id.btnPlay3);
        comPlay[0][3] = findViewById(R.id.btnPlay4);
        comPlay[0][4] = findViewById(R.id.btnPlay5);

        //Second Row
        comPlay[1][0] = findViewById(R.id.btnPlay6);
        comPlay[1][1] = findViewById(R.id.btnPlay7);
        comPlay[1][2] = findViewById(R.id.btnPlay8);
        comPlay[1][3] = findViewById(R.id.btnPlay9);
        comPlay[1][4] = findViewById(R.id.btnPlay10);

        //Third Row
        comPlay[2][0] = findViewById(R.id.btnPlay11);
        comPlay[2][1] = findViewById(R.id.btnPlay12);
        comPlay[2][2] = findViewById(R.id.btnPlay13);
        comPlay[2][3] = findViewById(R.id.btnPlay14);
        comPlay[2][4] = findViewById(R.id.btnPlay15);

        //Forth Row
        comPlay[3][0] = findViewById(R.id.btnPlay16);
        comPlay[3][1] = findViewById(R.id.btnPlay17);
        comPlay[3][2] = findViewById(R.id.btnPlay18);
        comPlay[3][3] = findViewById(R.id.btnPlay19);
        comPlay[3][4] = findViewById(R.id.btnPlay20);

        //Fifth Row
        comPlay[4][0] = findViewById(R.id.btnPlay21);
        comPlay[4][1] = findViewById(R.id.btnPlay22);
        comPlay[4][2] = findViewById(R.id.btnPlay23);
        comPlay[4][3] = findViewById(R.id.btnPlay24);
        comPlay[4][4] = findViewById(R.id.btnPlay25);

        humanPlay();

    }

    /**Handles the OnClickListener of all moves
    //this is achieved using implements View.OnClickListener at Class declaration
    //using this implementation helps to easily check the button clicked
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnPlay1:
                comPlay[0][0].setText(playerOneMoveType);
                boardStatus[0][0] = true;
                comPlay[0][0].setEnabled(false);

                break;

            case R.id.btnPlay2:
                comPlay[0][1].setText(playerOneMoveType);
                boardStatus[0][1] = true;
                comPlay[0][1].setEnabled(false);
                break;

            case R.id.btnPlay3:
                comPlay[0][2].setText(playerOneMoveType);
                boardStatus[0][2] = true;
                comPlay[0][2].setEnabled(false);

                break;

            case R.id.btnPlay4:
                comPlay[0][3].setText(playerOneMoveType);
                boardStatus[0][3] = true;
                comPlay[0][3].setEnabled(false);

                break;
            case R.id.btnPlay5:
                comPlay[0][4].setText(playerOneMoveType);
                boardStatus[0][4] = true;
                comPlay[0][4].setEnabled(false);
                break;

            case R.id.btnPlay6:
                comPlay[1][0].setText(playerOneMoveType);
                boardStatus[1][0] = true;
                comPlay[1][0].setEnabled(false);

                break;

            case R.id.btnPlay7:
                comPlay[1][1].setText(playerOneMoveType);
                boardStatus[1][1] = true;
                comPlay[1][1].setEnabled(false);

                break;

            case R.id.btnPlay8:

                comPlay[1][2].setText(playerOneMoveType);
                boardStatus[1][2] = true;
                comPlay[1][2].setEnabled(false);

                break;

            case R.id.btnPlay9:
                comPlay[1][3].setText(playerOneMoveType);
                boardStatus[1][3] = true;
                comPlay[1][3].setEnabled(false);
                break;

                case R.id.btnPlay10:
                comPlay[1][4].setText(playerOneMoveType);
                boardStatus[1][4] = true;
                comPlay[1][4].setEnabled(false);
                break;

            case R.id.btnPlay11:
                comPlay[2][0].setText(playerOneMoveType);
                boardStatus[2][0] = true;
                comPlay[2][0].setEnabled(false);
                break;
            case R.id.btnPlay12:
                comPlay[2][1].setText(playerOneMoveType);
                boardStatus[2][1] = true;
                comPlay[2][1].setEnabled(false);
                break;
            case R.id.btnPlay13:
                comPlay[2][2].setText(playerOneMoveType);
                boardStatus[2][2] = true;
                comPlay[2][2].setEnabled(false);
                break;
            case R.id.btnPlay14:
                comPlay[2][3].setText(playerOneMoveType);
                boardStatus[2][3] = true;
                comPlay[2][3].setEnabled(false);
                break;
            case R.id.btnPlay15:
                comPlay[2][4].setText(playerOneMoveType);
                boardStatus[2][4] = true;
                comPlay[2][4].setEnabled(false);
                break;
            case R.id.btnPlay16:
                comPlay[3][0].setText(playerOneMoveType);
                boardStatus[3][0] = true;
                comPlay[3][0].setEnabled(false);
                break;
            case R.id.btnPlay17:
                comPlay[3][1].setText(playerOneMoveType);
                boardStatus[3][1] = true;
                comPlay[3][1].setEnabled(false);
                break;
            case R.id.btnPlay18:
                comPlay[3][2].setText(playerOneMoveType);
                boardStatus[3][2] = true;
                comPlay[3][2].setEnabled(false);
                break;
            case R.id.btnPlay19:
                comPlay[3][3].setText(playerOneMoveType);
                boardStatus[3][3] = true;
                comPlay[3][3].setEnabled(false);
                break;
            case R.id.btnPlay20:
                comPlay[3][4].setText(playerOneMoveType);
                boardStatus[3][4] = true;
                comPlay[3][4].setEnabled(false);
                break;
            case R.id.btnPlay21:
                comPlay[4][0].setText(playerOneMoveType);
                boardStatus[4][0] = true;
                comPlay[4][0].setEnabled(false);
                break;
            case R.id.btnPlay22:
                comPlay[4][1].setText(playerOneMoveType);
                boardStatus[4][1] = true;
                comPlay[4][1].setEnabled(false);
                break;
            case R.id.btnPlay23:
                comPlay[4][2].setText(playerOneMoveType);
                boardStatus[4][2] = true;
                comPlay[4][2].setEnabled(false);
                break;
            case R.id.btnPlay24:
                comPlay[4][3].setText(playerOneMoveType);
                boardStatus[4][3] = true;
                comPlay[4][3].setEnabled(false);
                break;
            case R.id.btnPlay25:
                comPlay[4][4].setText(playerOneMoveType);
                boardStatus[4][4] = true;
                comPlay[4][4].setEnabled(false);
                break;
            default:
                break;
        }
        jointMoves++;
        //Check if the human player has won
        checkWinner();

        if (jointMoves == 13 && win == 0) {
            gamePlayed=gamePlayed+1;
            gamePlay.setText("Game Played: "+gamePlayed);
            restart.setText("Play Again");
            int draws = gamePlayed - scoreOne - scoreTwo;
            gameDraw.setText("Game Draw: "+ draws);
            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
            View mView = layoutInflaterAndroid.inflate(R.layout.draw, null);
            AlertDialog.Builder winDialog = new AlertDialog.Builder(c);
            winDialog.setView(mView);
            winDialog
                    .setCancelable(true);
            AlertDialog alertDialogAndroid = winDialog.create();
            alertDialogAndroid.show();
            Toast.makeText(this, "Game end in Draw ", Toast.LENGTH_SHORT).show();
            disable();
        } else if (jointMoves <= 25 && (win == 1 || win == 2)) {
            if (win == 1) {
                gamePlayed=gamePlayed+1;
                gamePlay.setText("Game Played: "+gamePlayed);
                scoreOne = scoreOne + 1;
                scorePlayer.setText("" + scoreOne);
            }
            disable();
            result();
            //initiate the computer turn
        } else {
            if (level.getText().toString().matches("Easy")) {
                computerPlayEasy();
            } else if (level.getText().toString().matches("Hard")) {
                computerPlayHard();
            } else if (level.getText().toString().matches("Expert")) {
                computerPlayExpert();

            }
            checkWinner();
            if (jointMoves <= 25 && (win == 1 || win == 2)) {
                if (win == 2) {
                    gamePlayed=gamePlayed+1;
                    gamePlay.setText("Game Played: "+gamePlayed);
                    scoreTwo = scoreTwo + 1;
                    scoreCom.setText("" + scoreTwo);
                }
                disable();
                result();
            }
        }
    }

    /**
     * this method declare the winner
     */
    private void result() {

        disable();
        restart.setText("Play Again");
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.win, null);
        AlertDialog.Builder winDialog = new AlertDialog.Builder(c);
        winDialog.setView(mView);
        final TextView winName = mView.findViewById(R.id.winnerName);
        winName.setText("" + WinnnerName);
         winDialog
                .setCancelable(true);
        AlertDialog alertDialogAndroid = winDialog.create();
        alertDialogAndroid.show();
        Toast.makeText(this, "Winner " + " " + WinnnerName, Toast.LENGTH_SHORT).show();


    }

    /**
     * This method handles the onClickListener of Human Player
     */
    private void humanPlay() {
        //OnClickListener for each button
        //First Row
        comPlay[0][0].setOnClickListener(this);
        comPlay[0][1].setOnClickListener(this);
        comPlay[0][2].setOnClickListener(this);
        comPlay[0][3].setOnClickListener(this);
        comPlay[0][4].setOnClickListener(this);

        //Second Row
        comPlay[1][0].setOnClickListener(this);
        comPlay[1][1].setOnClickListener(this);
        comPlay[1][2].setOnClickListener(this);
        comPlay[1][3].setOnClickListener(this);
        comPlay[1][4].setOnClickListener(this);

        //Third Row
        comPlay[2][0].setOnClickListener(this);
        comPlay[2][1].setOnClickListener(this);
        comPlay[2][2].setOnClickListener(this);
        comPlay[2][3].setOnClickListener(this);
        comPlay[2][4].setOnClickListener(this);

        //Forth Row
        comPlay[3][0].setOnClickListener(this);
        comPlay[3][1].setOnClickListener(this);
        comPlay[3][2].setOnClickListener(this);
        comPlay[3][3].setOnClickListener(this);
        comPlay[3][4].setOnClickListener(this);

        //Fifth Row
        comPlay[4][0].setOnClickListener(this);
        comPlay[4][1].setOnClickListener(this);
        comPlay[4][2].setOnClickListener(this);
        comPlay[4][3].setOnClickListener(this);
        comPlay[4][4].setOnClickListener(this);

    }

    /**
     * This method handles the computer play,
     * the computer is meant to play a base on the difficulty level
     */

    private void computerPlayEasy() {
        int mov1, mov2;
        //Random play
        do {
            mov1 = mRand.nextInt(5);
            mov2 = mRand.nextInt(5);
        }
        while (comPlay[mov1][mov2].getText().toString().matches(playerOneMoveType) || comPlay[mov1][mov2].getText().toString().matches(comMoveType));
        comPlay[mov1][mov2].setText(comMoveType);
        comPlay[mov1][mov2].setEnabled(false);
    }

    private void computerPlayHard() {
        int mov1, mov2;
        // First see if there's a move computer can make to win
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (comPlay[i][j].getText().toString().matches("")) {
                    String curr = "";
                    comPlay[i][j].setText(comMoveType);
                    checkWinner();
                    if (win == 2) {
//                        continue here;
//                        disable();
//                        result();
                        return;
                    } else
                        comPlay[i][j].setText(curr);
                }
            }
        }
        //Random play
        do {
            mov1 = mRand.nextInt(5);
            mov2 = mRand.nextInt(5);
        }
        while (comPlay[mov1][mov2].getText().toString().matches(playerOneMoveType) || comPlay[mov1][mov2].getText().toString().matches(comMoveType));
        comPlay[mov1][mov2].setText(comMoveType);
        comPlay[mov1][mov2].setEnabled(false);
    }

    private void computerPlayExpert() {
        int mov1, mov2;
        // First see if there's a move computer can make to win
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (comPlay[i][j].getText().toString().matches("")) {
                    String curr = "";
                    comPlay[i][j].setText(comMoveType);
                    checkWinner();
                    if (win == 2) {
//                        disable();
//                        result();
                        return;
                    } else
                        comPlay[i][j].setText(curr);
                }
            }
        }
        // See if there's a move computer can make to block X from winning
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (comPlay[i][j].getText().toString().matches("")) {
                    String curr = "";
                    comPlay[i][j].setText(playerOneMoveType);
                    checkWinner();
                    if (win == 1) {
                        comPlay[i][j].setText(comMoveType);
                        win = 0;
                        comPlay[i][j].setEnabled(false);
                        return;
                    } else
                        comPlay[i][j].setText(curr);
                }
            }
        }
        //Random play
        do {
            mov1 = mRand.nextInt(5);
            mov2 = mRand.nextInt(5);
        }
        while (comPlay[mov1][mov2].getText().toString().matches(playerOneMoveType) || comPlay[mov1][mov2].getText().toString().matches(comMoveType));
        comPlay[mov1][mov2].setText(comMoveType);
        comPlay[mov1][mov2].setEnabled(false);

    }

    /**
     * @return this method is to decide who win the gama
     * //A player can win the game in by checking boxes in any of the following
     * // 3 rows, 3 columns and 2 diagonals
     */
    private void checkWinner() {
        //1st Column check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[0][1].getText().toString().matches("") & !comPlay[0][2].getText().toString().matches("") & !comPlay[0][3].getText().toString().matches("") & !comPlay[0][4].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[0][1].getText().toString())&& comPlay[0][0].getText().toString().matches(comPlay[0][2].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[0][3].getText().toString())&& comPlay[0][0].getText().toString().matches(comPlay[0][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //2nd Column check
        if (!comPlay[1][0].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[1][2].getText().toString().matches("") & !comPlay[1][3].getText().toString().matches("") & !comPlay[1][4].getText().toString().matches("")) {
            if (comPlay[1][0].getText().toString().matches(comPlay[1][1].getText().toString())&& comPlay[1][0].getText().toString().matches(comPlay[1][2].getText().toString())
                    && comPlay[1][0].getText().toString().matches(comPlay[1][3].getText().toString())&& comPlay[1][0].getText().toString().matches(comPlay[1][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[1][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //3rd Column check
        if (!comPlay[2][0].getText().toString().matches("") & !comPlay[2][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("") & !comPlay[2][3].getText().toString().matches("") & !comPlay[2][4].getText().toString().matches("")) {
            if (comPlay[2][0].getText().toString().matches(comPlay[2][1].getText().toString())&& comPlay[2][0].getText().toString().matches(comPlay[2][2].getText().toString())
                    && comPlay[2][0].getText().toString().matches(comPlay[2][3].getText().toString())&& comPlay[2][0].getText().toString().matches(comPlay[2][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[2][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //4th Column check
        if (!comPlay[3][0].getText().toString().matches("") & !comPlay[3][1].getText().toString().matches("") & !comPlay[3][2].getText().toString().matches("") & !comPlay[3][3].getText().toString().matches("") & !comPlay[3][4].getText().toString().matches("")) {
            if (comPlay[3][0].getText().toString().matches(comPlay[3][1].getText().toString())&& comPlay[3][0].getText().toString().matches(comPlay[3][2].getText().toString())
                    && comPlay[3][0].getText().toString().matches(comPlay[3][3].getText().toString())&& comPlay[3][0].getText().toString().matches(comPlay[3][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[3][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //5th Column check
        if (!comPlay[4][0].getText().toString().matches("") & !comPlay[4][1].getText().toString().matches("") & !comPlay[4][2].getText().toString().matches("") & !comPlay[4][3].getText().toString().matches("") & !comPlay[4][4].getText().toString().matches("")) {
            if (comPlay[4][0].getText().toString().matches(comPlay[4][1].getText().toString())&& comPlay[4][0].getText().toString().matches(comPlay[4][2].getText().toString())
                    && comPlay[4][0].getText().toString().matches(comPlay[4][3].getText().toString())&& comPlay[4][0].getText().toString().matches(comPlay[4][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[4][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //1st Row check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[1][0].getText().toString().matches("") & !comPlay[2][0].getText().toString().matches("") & !comPlay[3][0].getText().toString().matches("") & !comPlay[4][0].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[1][0].getText().toString())&& comPlay[0][0].getText().toString().matches(comPlay[2][0].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[3][0].getText().toString())&& comPlay[0][0].getText().toString().matches(comPlay[4][0].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //2nd Row check
        if (!comPlay[0][1].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][1].getText().toString().matches("") & !comPlay[3][1].getText().toString().matches("") & !comPlay[1][4].getText().toString().matches("")) {
            if (comPlay[0][1].getText().toString().matches(comPlay[1][1].getText().toString())&& comPlay[0][1].getText().toString().matches(comPlay[2][1].getText().toString())
                    && comPlay[0][1].getText().toString().matches(comPlay[3][1].getText().toString())&& comPlay[0][1].getText().toString().matches(comPlay[4][1].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][1].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //3rd Row check
        if (!comPlay[0][2].getText().toString().matches("") & !comPlay[1][2].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("") & !comPlay[3][2].getText().toString().matches("") & !comPlay[4][2].getText().toString().matches("")) {
            if (comPlay[0][2].getText().toString().matches(comPlay[1][2].getText().toString())&& comPlay[0][2].getText().toString().matches(comPlay[2][2].getText().toString())
                    && comPlay[0][2].getText().toString().matches(comPlay[3][2].getText().toString())&& comPlay[0][2].getText().toString().matches(comPlay[4][2].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][2].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //4th Row check
        if (!comPlay[0][3].getText().toString().matches("") & !comPlay[1][3].getText().toString().matches("") & !comPlay[2][3].getText().toString().matches("") & !comPlay[3][3].getText().toString().matches("") & !comPlay[4][3].getText().toString().matches("")) {
            if (comPlay[0][3].getText().toString().matches(comPlay[1][3].getText().toString())&& comPlay[0][3].getText().toString().matches(comPlay[2][3].getText().toString())
                    && comPlay[0][3].getText().toString().matches(comPlay[3][3].getText().toString())&& comPlay[0][3].getText().toString().matches(comPlay[4][3].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][3].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //5th Row check
        if (!comPlay[0][4].getText().toString().matches("") & !comPlay[1][4].getText().toString().matches("") & !comPlay[2][4].getText().toString().matches("") & !comPlay[3][4].getText().toString().matches("") & !comPlay[4][4].getText().toString().matches("")) {
            if (comPlay[0][4].getText().toString().matches(comPlay[1][4].getText().toString())&& comPlay[0][4].getText().toString().matches(comPlay[2][4].getText().toString())
                    && comPlay[0][4].getText().toString().matches(comPlay[3][4].getText().toString())&& comPlay[0][4].getText().toString().matches(comPlay[4][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][4].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }

        //1st Dia check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("") & !comPlay[3][3].getText().toString().matches("") & !comPlay[4][4].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[1][1].getText().toString())&& comPlay[0][0].getText().toString().matches(comPlay[2][2].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[3][3].getText().toString())&& comPlay[0][0].getText().toString().matches(comPlay[4][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //2nd Dia check
        if (!comPlay[4][0].getText().toString().matches("") & !comPlay[3][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("") & !comPlay[1][3].getText().toString().matches("") & !comPlay[0][4].getText().toString().matches("")) {
            if (comPlay[4][0].getText().toString().matches(comPlay[3][1].getText().toString())&& comPlay[4][0].getText().toString().matches(comPlay[2][2].getText().toString())
                    && comPlay[4][0].getText().toString().matches(comPlay[1][3].getText().toString())&& comPlay[4][0].getText().toString().matches(comPlay[0][4].getText().toString())) {
                //Toast.makeText(this, "col1", Toast.LENGTH_SHORT).show();
                if (comPlay[4][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent playNew = new Intent(singleVsCom5X5.this, MainActivity.class);
        startActivity(playNew);
    }

    /**
     * This method reset the board for a fresh round
     */

    private void restart() {
        restart.setText("Reset");
        //enable all moves
        jointMoves = 0;
        win = 0;

        //First Row
        comPlay[0][0].setEnabled(true);
        comPlay[0][1].setEnabled(true);
        comPlay[0][2].setEnabled(true);
        comPlay[0][3].setEnabled(true);
        comPlay[0][4].setEnabled(true);

        //Second Row
        comPlay[1][0].setEnabled(true);
        comPlay[1][1].setEnabled(true);
        comPlay[1][2].setEnabled(true);
        comPlay[1][3].setEnabled(true);
        comPlay[1][4].setEnabled(true);

        //Third Row
        comPlay[2][0].setEnabled(true);
        comPlay[2][1].setEnabled(true);
        comPlay[2][2].setEnabled(true);
        comPlay[2][3].setEnabled(true);
        comPlay[2][4].setEnabled(true);

        //Forth Row
        comPlay[3][0].setEnabled(true);
        comPlay[3][1].setEnabled(true);
        comPlay[3][2].setEnabled(true);
        comPlay[3][3].setEnabled(true);
        comPlay[3][4].setEnabled(true);

        //Fifth Row
        comPlay[4][0].setEnabled(true);
        comPlay[4][1].setEnabled(true);
        comPlay[4][2].setEnabled(true);
        comPlay[4][3].setEnabled(true);
        comPlay[4][4].setEnabled(true);

        //clear all moves

        //First Row
        comPlay[0][0].setText("");
        comPlay[0][1].setText("");
        comPlay[0][2].setText("");
        comPlay[0][3].setText("");
        comPlay[0][4].setText("");

        //Second Row
        comPlay[1][0].setText("");
        comPlay[1][1].setText("");
        comPlay[1][2].setText("");
        comPlay[1][3].setText("");
        comPlay[1][4].setText("");

        //Third Row
        comPlay[2][0].setText("");
        comPlay[2][1].setText("");
        comPlay[2][2].setText("");
        comPlay[2][3].setText("");
        comPlay[2][4].setText("");

        //Forth Row
        comPlay[3][0].setText("");
        comPlay[3][1].setText("");
        comPlay[3][2].setText("");
        comPlay[3][3].setText("");
        comPlay[3][4].setText("");

        //Fifth Row
        comPlay[4][0].setText("");
        comPlay[4][1].setText("");
        comPlay[4][2].setText("");
        comPlay[4][3].setText("");
        comPlay[4][4].setText("");
    }

    /**
     * Disable all button
     */
    private void disable() {
        //First Row
        comPlay[0][0].setEnabled(false);
        comPlay[0][1].setEnabled(false);
        comPlay[0][2].setEnabled(false);
        comPlay[0][3].setEnabled(false);
        comPlay[0][4].setEnabled(false);

        //Second Row
        comPlay[1][0].setEnabled(false);
        comPlay[1][1].setEnabled(false);
        comPlay[1][2].setEnabled(false);
        comPlay[1][3].setEnabled(false);
        comPlay[1][4].setEnabled(false);

        //Third Row
        comPlay[2][0].setEnabled(false);
        comPlay[2][1].setEnabled(false);
        comPlay[2][2].setEnabled(false);
        comPlay[2][3].setEnabled(false);
        comPlay[2][4].setEnabled(false);

        //Forth Row
        comPlay[3][0].setEnabled(false);
        comPlay[3][1].setEnabled(false);
        comPlay[3][2].setEnabled(false);
        comPlay[3][3].setEnabled(false);
        comPlay[3][4].setEnabled(false);

        //Fifth Row
        comPlay[4][0].setEnabled(false);
        comPlay[4][1].setEnabled(false);
        comPlay[4][2].setEnabled(false);
        comPlay[4][3].setEnabled(false);
        comPlay[4][4].setEnabled(false);
    }

}


