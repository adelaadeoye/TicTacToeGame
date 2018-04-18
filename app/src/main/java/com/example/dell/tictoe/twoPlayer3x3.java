
/*
 * Copyright (c) 2018. This app is work of great ideas and reference to other online resources. If you will have to make use of any part of the content please go on but don't hesitate to reference me @github.com/adelaadeoye
 */

package com.example.dell.tictoe;

import android.annotation.SuppressLint;
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

/**
 * this is the two player aspect of the game each player takes turn to play
 */
public class twoPlayer3x3 extends AppCompatActivity implements View.OnClickListener {
    private int jointMoves = 0;
    private boolean playerOneTurn = true;
    private TextView playerOneName;
    private TextView playerTwoName;
    private TextView scoreOneScore;
    private TextView scoreTwoScore;
    private TextView gamePlay;
    private TextView gameDraw;
    private Button player1;
    private Button player2;
    private Button restart;
    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    private final boolean[][] boardStatus = new boolean[3][3];
    private final Button[][] comPlay = new Button[3][3];
    private String WinnnerName;
    private String playerOneMoveType;
    private String playerTwoMoveType;
    private int scoreOne = 0;
    private int scoreTwo = 0;
    private int win = 0;
    private int gamePlayed = 0;
    private final Context c = this;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player3x3);
        //Score board
        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);
        scoreOneScore = findViewById(R.id.scoreOneScore);
        scoreTwoScore = findViewById(R.id.scoreTwoScore);
        gamePlay = findViewById(R.id.gamePlayed);
        gameDraw = findViewById(R.id.gameDraw);


        //get all the play buttons id's
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
//        TextView textView = findViewById(R.id.testing);
        //Player names
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String moveType = (String) bundle.get("moveType");
//            textView.setText(moveType);
            assert moveType != null;
            if ("X".matches(moveType)) {
                playerOneMoveType = "X";
                playerTwoMoveType = "O";
            } else if ("O".matches(moveType)) {
                playerOneMoveType = "O";
                playerTwoMoveType = "X";
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
            String playerTwo = (String) bundle.get("player2");
            player2.setText(playerTwo);
            if (player2.getText().toString().matches("")) {
                player2.setText("Player Two " + playerTwoMoveType + "'s Move");
                playerTwoName.setText("Player Two");
            } else {
                player2.setText(playerTwo + " " + playerTwoMoveType + "'s Move");
                playerTwoName.setText(playerTwo);
            }
        }

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

        //restart game
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

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


    /**Handles the OnClickListener of all moves
    //this is achieved using implements View.OnClickListener at Class declaration
    //using this implementation helps to easily check the button clicked
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnPlay1:
                if (playerOneTurn)
                //Player One Turn
                {
                    comPlay[0][0].setText(playerOneMoveType);
                    boardStatus[0][0] = true;
                } else
//                Player Two Turn
                {
                    comPlay[0][0].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[0][0].setEnabled(false);
                break;

            case R.id.btnPlay2:
                if (playerOneTurn)
//                Player One Turn
                {

                    comPlay[0][1].setText(playerOneMoveType);
                    boardStatus[0][1] = true;
                } else
//                Player Two Turn
                {

                    comPlay[0][1].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[0][1].setEnabled(false);
                break;

            case R.id.btnPlay3:
                if (playerOneTurn)
                //Player One Turn
                {

                    comPlay[0][2].setText(playerOneMoveType);
                    boardStatus[0][2] = true;
                } else
                //Player Two Turn
                {
                    comPlay[0][2].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[0][2].setEnabled(false);
                break;

            case R.id.btnPlay4:
                if (playerOneTurn)
                //Player One Turn
                {

                    comPlay[1][0].setText(playerOneMoveType);
                    boardStatus[1][0] = true;
                } else
                //Player Two Turn
                {

                    comPlay[1][0].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[1][0].setEnabled(false);
                break;

            case R.id.btnPlay5:
                if (playerOneTurn)
                //Player One Turn
                {

                    comPlay[1][1].setText(playerOneMoveType);
                    boardStatus[1][1] = true;
                } else
                //Player Two Turn
                {

                    comPlay[1][1].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[1][1].setEnabled(false);
                break;

            case R.id.btnPlay6:
                if (playerOneTurn)
                //Player One Turn
                {

                    comPlay[1][2].setText(playerOneMoveType);
                    boardStatus[1][2] = true;
                } else
                //Player Two Turn
                {

                    comPlay[1][2].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[1][2].setEnabled(false);
                break;

            case R.id.btnPlay7:
                if (playerOneTurn)
                //Player One Turn
                {

                    comPlay[2][0].setText(playerOneMoveType);
                    boardStatus[2][0] = true;
                } else
                //Player Two Turn
                {
                    comPlay[2][0].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[2][0].setEnabled(false);
                break;

            case R.id.btnPlay8:
                if (playerOneTurn)
                //Player One Turn
                {
                    comPlay[2][1].setText(playerOneMoveType);
                    boardStatus[2][1] = true;
                } else
                //Player Two Turn
                {

                    comPlay[2][1].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }
                comPlay[2][1].setEnabled(false);
                break;

            case R.id.btnPlay9:
                if (playerOneTurn)
                //Player One Turn
                {
                    comPlay[2][2].setText(playerOneMoveType);
                    boardStatus[2][2] = true;
                } else
                //Player Two Turn
                {
                    comPlay[2][2].setText(playerTwoMoveType);
                    boardStatus[0][0] = false;
                }

                comPlay[2][2].setEnabled(false);
                break;

            default:
                break;
        }

        //After each move/play the jointMoves is incremented
        jointMoves++;
        playerOneTurn = !playerOneTurn;

        //Each Player is notified of his/her turn
        if (playerOneTurn) {
            player1.setVisibility(View.VISIBLE);
            player2.setVisibility(View.INVISIBLE);

        } else {
            player1.setVisibility(View.INVISIBLE);
            player2.setVisibility(View.VISIBLE);

        }
        //This method is call at each round of play to determine the winner of the game
        checkWinner();
        // At each Round of play the game will continually check the moves made by the players to determine
        //if the game ends in draw
        if (jointMoves == 9 && win == 0) {
            gamePlayed = gamePlayed + 1;
            gamePlay.setText("Game Played: " + gamePlayed);
            restart.setText("Play Again");
            int draws = gamePlayed - scoreOne - scoreTwo;
            gameDraw.setText("Game Draw: " + draws);
            restart.setText("Play Again");
            Toast.makeText(this, "Game end in Draw", Toast.LENGTH_SHORT).show();
            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
            @SuppressLint("InflateParams") View mView = layoutInflaterAndroid.inflate(R.layout.draw, null);
            AlertDialog.Builder winDialog = new AlertDialog.Builder(c);
            winDialog.setView(mView);
            winDialog
                    .setCancelable(true);
            AlertDialog alertDialogAndroid = winDialog.create();
            alertDialogAndroid.show();
        } else if (jointMoves <= 9 && (win == 1 || win == 2)) {
            if (win == 1) {
                scoreOne = scoreOne + 1;
                scoreOneScore.setText("" + scoreOne);
                gamePlayed = gamePlayed + 1;
                gamePlay.setText("Game Played: " + gamePlayed);
            } else {
                scoreTwo = scoreTwo + 1;
                scoreTwoScore.setText("" + scoreTwo);
                gamePlayed = gamePlayed + 1;
                gamePlay.setText("Game Played: " + gamePlayed);
            }

            disable();
            result();
        }


    }

    /**
     * this method declare the winner
     */
    @SuppressLint("SetTextI18n")
    private void result() {
        restart.setText("Play Again");
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        @SuppressLint("InflateParams") View mView = layoutInflaterAndroid.inflate(R.layout.win, null);
        AlertDialog.Builder winDialog = new AlertDialog.Builder(c);
        winDialog.setView(mView);
        final TextView winName = mView.findViewById(R.id.winnerName);
        winName.setText("" + WinnnerName);
         winDialog
                .setCancelable(true);
        AlertDialog alertDialogAndroid = winDialog.create();
        alertDialogAndroid.show();
        Toast.makeText(this, "Winner " + WinnnerName, Toast.LENGTH_SHORT).show();
    }

    /**
     * @return this method is to decide who win the gama
     * //A player can win the game in by checking boxes in any of the following
     * // 3 rows, 3 columns and 2 diagonals
     */

    private void checkWinner() {

        //1st Column check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[0][1].getText().toString().matches("") & !comPlay[0][2].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[0][1].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[0][2].getText().toString())) {
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;
                    //

                }
            }
        }
        //2nd Column check
        if (!comPlay[1][0].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[1][2].getText().toString().matches("")) {
            if (comPlay[1][0].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[1][0].getText().toString().matches(comPlay[1][2].getText().toString())) {
                if (comPlay[1][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;


                }
            }
        }
        //3rd Column check
        if (!comPlay[2][0].getText().toString().matches("") & !comPlay[2][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("")) {
            if (comPlay[2][0].getText().toString().matches(comPlay[2][1].getText().toString())
                    && comPlay[2][0].getText().toString().matches(comPlay[2][2].getText().toString())) {
                if (comPlay[2][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;


                }
            }
        }
        //1st Row check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[1][0].getText().toString().matches("") & !comPlay[2][0].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[1][0].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[2][0].getText().toString())) {
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;


                }
            }
        }
        //2nd Row check
        if (!comPlay[0][1].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][1].getText().toString().matches("")) {
            if (comPlay[0][1].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[0][1].getText().toString().matches(comPlay[2][1].getText().toString())) {
                if (comPlay[0][1].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;


                }
            }
        }
        //3rd Row check
        if (!comPlay[0][2].getText().toString().matches("") & !comPlay[1][2].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("")) {
            if (comPlay[0][2].getText().toString().matches(comPlay[1][2].getText().toString())
                    && comPlay[0][2].getText().toString().matches(comPlay[2][2].getText().toString())) {
                if (comPlay[0][2].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;

                }
            }
        }

        //1st Diagonal check
        if (!comPlay[0][0].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][2].getText().toString().matches("")) {
            if (comPlay[0][0].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[0][0].getText().toString().matches(comPlay[2][2].getText().toString())) {
                if (comPlay[0][0].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;


                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;


                }
            }
        }

        //2nd Diagonal check
        if (!comPlay[0][2].getText().toString().matches("") & !comPlay[1][1].getText().toString().matches("") & !comPlay[2][0].getText().toString().matches("")) {
            if (comPlay[0][2].getText().toString().matches(comPlay[1][1].getText().toString())
                    && comPlay[0][2].getText().toString().matches(comPlay[2][0].getText().toString())) {
                if (comPlay[0][2].getText().toString().matches(playerOneMoveType)) {
                    WinnnerName = playerOneName.getText().toString();
                    win = 1;

                } else {
                    WinnnerName = playerTwoName.getText().toString();
                    win = 2;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent playNew = new Intent(twoPlayer3x3.this, MainActivity.class);
        startActivity(playNew);
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

    /**
     * This method clear the game
     */
    @SuppressLint("SetTextI18n")
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

}