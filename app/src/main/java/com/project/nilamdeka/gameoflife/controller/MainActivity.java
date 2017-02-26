package com.project.nilamdeka.gameoflife.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.nilamdeka.gameoflife.R;
import com.project.nilamdeka.gameoflife.model.GameOfLife;
import com.project.nilamdeka.gameoflife.view.GameBoard;

public class MainActivity extends AppCompatActivity {

    private GameBoard gameBoard;
    private GameOfLife gameOfLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameOfLife = new GameOfLife(getResources().getInteger(R.integer.game_board_row),
                getResources().getInteger(R.integer.game_board_col));
        gameBoard = (GameBoard) findViewById(R.id.game_board);
    }

    public void reset(View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:
                        reset();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirm_msg))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener).show();

    }

    private void reset() {
        gameOfLife.resetGame();

        gameBoard.setPopulation(gameOfLife.getSeed());
        gameBoard.invalidate();
    }

    public void next(View view) {

        gameOfLife.updateSeed(gameBoard.getPopulation());
        gameOfLife.generateNextGen();
        gameOfLife.updateSeed(gameOfLife.getNxtGen());

        gameBoard.setPopulation(gameOfLife.getNxtGen());
        gameBoard.invalidate();
    }

}
