
/*
 * Copyright (c) 2018. This app is work of great ideas and reference to
 * other online resources. If you will have to make use of any part of the content please go on but don't hesitate to reference me @github.com/adelaadeoye
 */

package com.example.dell.tictoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * This class is the main class for selection of game mode
 */

public class MainActivity extends AppCompatActivity {
    private final Context c = this;
    private Intent singlePlay;
    private Intent doublePlay;
    private boolean s3 = false;
    private boolean s5 = false;
    private boolean t3 = false;
    private boolean t5 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//Each game options can be seclected and play
        Button single3 = findViewById(R.id.single3);
        Button double3 = findViewById(R.id.double3);
        Button single5 = findViewById(R.id.single5);
        Button double5 = findViewById(R.id.double5);

//SingleVsComputer 3x3 Board
        single3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s3 = true;
                userComDialog();
            }
        });
//SingleVsComputer 5x5 Board
        single5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s5 = true;
                userComDialog();
            }

        });
//Two Players 3x3 Board        
        double3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3=true;
                twoPlayer();
            }
        });
//Two Players 5x5 Board
        double5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5=true;
                twoPlayer();
            }
        });
    }

    /**
     * This is the method that accept userInput such as name,moveType, difficulty level when the game version against computer is selected
     */
    private void userComDialog() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        @SuppressLint("InflateParams") View mView = layoutInflaterAndroid.inflate(R.layout.activity_com_play_name,null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setTitle("Player Name");
        alertDialogBuilderUserInput.setView(mView);
        final EditText playerOne = mView.findViewById(R.id.playerOne);

        //Select Move type
        final RadioButton xMove = mView.findViewById(R.id.xMove);
        final RadioButton oMove = mView.findViewById(R.id.oMove);
        final RadioButton easy = mView.findViewById(R.id.easy);
        final RadioButton hard = mView.findViewById(R.id.hard);
        final RadioButton expert = mView.findViewById(R.id.expert);
         alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here

                        if (s3 ) {
                            singlePlay = new Intent(MainActivity.this, singleVsCom3x3.class);
                        }
                        else if(s5){
                            singlePlay = new Intent(MainActivity.this, singleVsCom5X5.class);
                        }
                        //1st Player name
                        String player1 = playerOne.getText().toString();
                        singlePlay.putExtra("player1", player1);
                        //Move Type
                        String moveType;
                        if (xMove.isChecked()) {
                            moveType = "X";
                            singlePlay.putExtra("moveType", moveType);
                        } else if (oMove.isChecked()) {
                            moveType = "O";
                            singlePlay.putExtra("moveType", moveType);
                        }
                        //Difficulty Level
                        String difficultyLevel;
                        if (easy.isChecked()) {
                            difficultyLevel = "Easy";
                            singlePlay.putExtra("difficultyLevel", difficultyLevel);
                        } else if (hard.isChecked()) {
                            difficultyLevel = "Hard";
                            singlePlay.putExtra("difficultyLevel", difficultyLevel);
                        } else if (expert.isChecked()) {
                            difficultyLevel = "Expert";
                            singlePlay.putExtra("difficultyLevel", difficultyLevel);
                        }

                        // Start the new activity
                        startActivity(singlePlay);
                    }
                })

                .setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
    /**
     * This is the method that accept userInput such as name,moveType,  when the game version against human player is selected
     */
    private void twoPlayer() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        @SuppressLint("InflateParams") View mView = layoutInflaterAndroid.inflate(R.layout.activity_players_name, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setTitle("Players Name");
        alertDialogBuilderUserInput.setView(mView);
        final EditText playerOne = mView.findViewById(R.id.playerOne);
        final EditText playerTwo = mView.findViewById(R.id.playerTwo);
        //Select Move type
        final RadioButton xMove = mView.findViewById(R.id.xMove);
        final RadioButton oMove = mView.findViewById(R.id.oMove);

         alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here

                        if(t3){
                             doublePlay = new Intent(MainActivity.this, twoPlayer3x3.class);
                        }
                        else if(t5){
                            //Todo put 5x5 here
                            doublePlay = new Intent(MainActivity.this, twoPlayer5x5.class);
                        }

                        //1st Player name
                        String player1 = playerOne.getText().toString();
                        doublePlay.putExtra("player1", player1);

                        //2nd Player name
                        String player2 = playerTwo.getText().toString();
                        doublePlay.putExtra("player2", player2);
                        String moveType;
                        if (xMove.isChecked()) {
                            moveType = "X";
                            doublePlay.putExtra("moveType", moveType);
                        } else if (oMove.isChecked()) {
                            moveType = "O";
                            doublePlay.putExtra("moveType", moveType);
                        }

                        // Start the new activity
                        startActivity(doublePlay);
                    }
                })

                .setNegativeButton("Back",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
