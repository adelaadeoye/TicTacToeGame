
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
public class singleVsCom3x3 extends AppCompatActivity implements View.OnClickListener {
    private int jointMoves = 0;
    private final boolean[][] boardStatus = new boolean[3][3];
    private final Button[][] comPlay = new Button[3][3];
    private int win = 0;
    private int scoreOne = 0;
    private int scoreTwo = 0;
    private int gamePlayed = 0;
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
    private final Context c = this;

    /**
     * This is the oncreate method that initiates the View
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_vs_com3_x3);
        mRand = new Random();

        //Score Board
        scorePlayer = findViewById(R.id.scorePlayer);
        scoreCom = findViewById(R.id.scoreCom);
        gamePlay = findViewById(R.id.gamePlayed);
        gameDraw = findViewById(R.id.gameDraw);
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
                player1.setText("Player One " + playerOneMoveType + "'s Move");
                playerOneName.setText("Player One");
            } else {
                player1.setText(playerOne + " " + playerOneMoveType + "'s Move");
                playerOneName.setText(playerOne);
            }
            //Level
            String levelType = (String) bundle.get("difficultyLevel");
            level.setText(levelType);


        }


        //Reset the board
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });
        //All the buttons that can initiate moves
        //First Row
        comPlay[0][0] = findViewById(R.id.btnPlay1);
        comPlay[0][1] = findViewById(R.id.btnPlay2);
        comPlay[0][2] = findViewById(R.id.btnPlay3);

        //Second Row
        comPlay[1][0] = findViewById(R.id.btnPlay4);
        comPlay[1][1] = findViewById(R.id.btnPlay5);
        comPlay[1][2] = findViewById(R.id.btnPlay6);

        //Third Row
        comPlay[2][0] = findViewById(R.id.btnPlay7);
        comPlay[2][1] = findViewById(R.id.btnPlay8);
        comPlay[2][2] = findViewById(R.id.btnPlay9);

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
                comPlay[1][0].setText(playerOneMoveType);
                boardStatus[1][0] = true;
                comPlay[1][0].setEnabled(false);

                break;
            case R.id.btnPlay5:
                comPlay[1][1].setText(playerOneMoveType);
                boardStatus[1][1] = true;
                comPlay[1][1].setEnabled(false);

                break;

            case R.id.btnPlay6:
                comPlay[1][2].setText(playerOneMoveType);
                boardStatus[1][2] = true;
                comPlay[1][2].setEnabled(false);

                break;

            case R.id.btnPlay7:
                comPlay[2][0].setText(playerOneMoveType);
                boardStatus[2][0] = true;
                comPlay[2][0].setEnabled(false);

                break;

            case R.id.btnPlay8:

                comPlay[2][1].setText(playerOneMoveType);
                boardStatus[2][1] = true;
                comPlay[2][1].setEnabled(false);

                break;

            case R.id.btnPlay9:
                comPlay[2][2].setText(playerOneMoveType);
                boardStatus[2][2] = true;
                comPlay[2][2].setEnabled(false);
                break;

            default:
                break;
        }
        //Increases the number of moves of human played
        jointMoves++;
        //Check if there is a win
        checkWinner();

        if (jointMoves == 5 && win == 0) {
            gamePlayed = gamePlayed + 1;
            gamePlay.setText("Game Played: " + gamePlayed);
            restart.setText("Play Again");
            int draws = gamePlayed - scoreOne - scoreTwo;
            gameDraw.setText("Game Draw: " + draws);
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
        } else if (jointMoves <= 5 && (win == 1 || win == 2)) {
            if (win == 1) {
                gamePlayed = gamePlayed + 1;
                gamePlay.setText("Game Played: " + gamePlayed);
                scoreOne = scoreOne + 1;
                scorePlayer.setText("" + scoreOne);
            }
            disable();
            result();
            //Computer play here
        } else {
            if (level.getText().toString().matches("Easy")) {
                computerPlayEasy();
            } else if (level.getText().toString().matches("Hard")) {
                computerPlayHard();
            } else if (level.getText().toString().matches("Expert")) {
                computerPlayExpert();

            }
            checkWinner();
            if (jointMoves <= 5 && (win == 1 || win == 2)) {
                if (win == 2) {
                    gamePlayed = gamePlayed + 1;
                    gamePlay.setText("Game Played: " + gamePlayed);
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
     * This thr method that handles the onClickListener
     */
    private void humanPlay() {
        //OnClickListener for each button
        comPlay[0][0].setOnClickListener(this);
        comPlay[0][1].setOnClickListener(this);
        comPlay[0][2].setOnClickListener(this);

        comPlay[1][0].setOnClickListener(this);
        comPlay[1][1].setOnClickListener(this);
        comPlay[1][2].setOnClickListener(this);

        comPlay[2][0].setOnClickListener(this);
        comPlay[2][1].setOnClickListener(this);
        comPlay[2][2].setOnClickListener(this);
    }

    /**
     * This method handles the computer play,
     * the computer is meant to play a base on the difficulty level
     */

    private void computerPlayEasy() {
        int mov1, mov2;
        //Random play
        do {
            mov1 = mRand.nextInt(3);
            mov2 = mRand.nextInt(3);
        }
        while (comPlay[mov1][mov2].getText().toString().matches(playerOneMoveType) || comPlay[mov1][mov2].getText().toString().matches(comMoveType));
        comPlay[mov1][mov2].setText(comMoveType);
        comPlay[mov1][mov2].setEnabled(false);
    }

    private void computerPlayHard() {
        int mov1, mov2;
        // First see if there's a move computer can make to win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
            mov1 = mRand.nextInt(3);
            mov2 = mRand.nextInt(3);
        }
        while (comPlay[mov1][mov2].getText().toString().matches(playerOneMoveType) || comPlay[mov1][mov2].getText().toString().matches(comMoveType));
        comPlay[mov1][mov2].setText(comMoveType);
        comPlay[mov1][mov2].setEnabled(false);
    }

    private void computerPlayExpert() {
        int mov1, mov2;
        // First see if there's a move computer can make to win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
            mov1 = mRand.nextInt(3);
            mov2 = mRand.nextInt(3);
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
    @SuppressWarnings("SameReturnValue")
    private void checkWinner() {
        //1st Column check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[0][1].getText().toString().matches("") & !comPlay[0][2].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[0][1].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[0][2].getText().toString())) {
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
        if (!comPlay[1][0].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[1][2].getText().toString().matches("")) {
            if (comPlay[1][0].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[1][0].getText().toString().matches(comPlay[1][2].getText().toString())) {
                //Toast.makeText(this, "col2", Toast.LENGTH_SHORT).show();
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
        if (!comPlay[2][0].getText().toString().matches("") & !comPlay[2][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("")) {
            if (comPlay[2][0].getText().toString().matches(comPlay[2][1].getText().toString()) && comPlay[2][0].getText().toString().matches(comPlay[2][2].getText().toString())) {
                //Toast.makeText(this, "col3", Toast.LENGTH_SHORT).show();
                if (comPlay[2][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;

                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }
        //1st Row check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[1][0].getText().toString().matches("") & !comPlay[2][0].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[1][0].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[2][0].getText().toString())) {
                //Toast.makeText(this, "row1", Toast.LENGTH_SHORT).show();
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
        if (!comPlay[0][1].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][1].getText().toString().matches("")) {
            if (comPlay[0][1].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[0][1].getText().toString().matches(comPlay[2][1].getText().toString())) {
                //Toast.makeText(this, "row2", Toast.LENGTH_SHORT).show();
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
        if (!comPlay[0][2].getText().toString().matches("") & !comPlay[1][2].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("")) {
            if (comPlay[0][2].getText().toString().matches(comPlay[1][2].getText().toString())
                    && comPlay[0][2].getText().toString().matches(comPlay[2][2].getText().toString())) {
                //Toast.makeText(this, "row3", Toast.LENGTH_SHORT).show();
                if (comPlay[0][2].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }

        //1st Diagonal check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[2][2].getText().toString())) {
                //Toast.makeText(this, "dia1", Toast.LENGTH_SHORT).show();
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;
                } else {
                    WinnnerName = "Computer";
                    win = 2;
                }
            }
        }

        //2nd Diagonal check
        if (!comPlay[0][2].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][0].getText().toString().matches("")) {
            if (comPlay[0][2].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[0][2].getText().toString().matches(comPlay[2][0].getText().toString())) {
                //Toast.makeText(this, "dia2", Toast.LENGTH_SHORT).show();
                if (comPlay[0][2].getText().toString().matches(playerOneMoveType)) {
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
        Intent playNew = new Intent(singleVsCom3x3.this, MainActivity.class);
        startActivity(playNew);
    }

    /**
     * This method Reset the Board for a fresh game
     */
    private void restart() {
        restart.setText("Reset");
        //enable all moves
        jointMoves = 0;
        win = 0;
        comPlay[0][0].setEnabled(true);
        comPlay[0][1].setEnabled(true);
        comPlay[0][2].setEnabled(true);

        comPlay[1][0].setEnabled(true);
        comPlay[1][1].setEnabled(true);
        comPlay[1][2].setEnabled(true);

        comPlay[2][0].setEnabled(true);
        comPlay[2][1].setEnabled(true);
        comPlay[2][2].setEnabled(true);

        //clear all moves
        comPlay[0][0].setText("");
        comPlay[0][1].setText("");
        comPlay[0][2].setText("");

        comPlay[1][0].setText("");
        comPlay[1][1].setText("");
        comPlay[1][2].setText("");

        comPlay[2][0].setText("");
        comPlay[2][1].setText("");
        comPlay[2][2].setText("");
    }

    /**
     * Disable all button
     */
    private void disable() {
        comPlay[0][0].setEnabled(false);
        comPlay[0][1].setEnabled(false);
        comPlay[0][2].setEnabled(false);

        comPlay[1][0].setEnabled(false);
        comPlay[1][1].setEnabled(false);
        comPlay[1][2].setEnabled(false);

        comPlay[2][0].setEnabled(false);
        comPlay[2][1].setEnabled(false);
        comPlay[2][2].setEnabled(false);
    }

}


