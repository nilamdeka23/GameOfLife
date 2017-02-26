package com.project.nilamdeka.gameoflife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.project.nilamdeka.gameoflife.R;
import com.project.nilamdeka.gameoflife.model.Array2DIndex;
import com.project.nilamdeka.gameoflife.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nilamdeka on 2/22/17.
 */
public class GameBoard extends View {

    private int row;
    private int col;

    private Paint gridPaint;
    private Paint circlePaint;

    private int measuredHeight;
    private int measuredWidth;

    private HashMap<Rect, Array2DIndex> map;

    private int[][] population;

    public void setPopulation(int[][] population) {
        Utils.copyArray2D(this.population, population);
    }

    public int[][] getPopulation() {
        return population;
    }

    public GameBoard(Context context) {
        super(context);

        row = context.getResources().getInteger(R.integer.game_board_col);
        col = context.getResources().getInteger(R.integer.game_board_row);

        init();
    }

    public GameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);

        row = context.getResources().getInteger(R.integer.game_board_col);
        col = context.getResources().getInteger(R.integer.game_board_row);

        init();
    }

    public GameBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        row = context.getResources().getInteger(R.integer.game_board_col);
        col = context.getResources().getInteger(R.integer.game_board_row);

        init();
    }

    public void init() {
        population = new int[row][col];

        // grid line paint
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(2);
        gridPaint.setColor(Color.GRAY);

        // circle population paint
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(2);
        circlePaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // view background color
        canvas.drawColor(Color.WHITE);

        // construction of the grid using rectangular fractals
        for (Map.Entry<Rect, Array2DIndex> entry : map.entrySet()) {

            Rect rect = entry.getKey();
            canvas.drawRect(rect, gridPaint);

            Array2DIndex array2DIndex = entry.getValue();

            if (population[array2DIndex.getIndexOne()][array2DIndex.getIndexTwo()] == 1) {
                canvas.drawCircle(rect.centerX(), rect.centerY(), Math.min(rect.width(),
                        rect.height()) / 3, circlePaint);
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            handleTouch(event.getX(), event.getY());
        }

        return false;
    }

    private void handleTouch(float x, float y) {

        // find the appropriate cell for the touch event
        for (Map.Entry<Rect, Array2DIndex> entry : map.entrySet()) {
            Rect rect = entry.getKey();

            if (rect.contains((int) x, (int) y)) {
                // handle touch
                Array2DIndex array2DIndex = entry.getValue();

                population[array2DIndex.getIndexOne()][array2DIndex.getIndexTwo()] = population[array2DIndex.getIndexOne()]
                        [array2DIndex.getIndexTwo()] == 1 ? 0 : 1;
                invalidate();
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measuredHeight = measureHeight(heightMeasureSpec);
        measuredWidth = measureWidth(widthMeasureSpec);

        // hack to avoid heavy lifting of object allocation on a draw cycle
        if (map == null) {
            map = new HashMap<>();

            for (int i = 0; i < row; i++) {

                for (int j = 0; j < col; j++) {

                    map.put(new Rect((int) interpolateX(j), (int) interpolateY(i),
                            (int) interpolateX(j + 1), (int) interpolateY(i + 1)), new Array2DIndex(i, j));

                }

            }
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureWidth(int widthMeasureSpec) {
        return MeasureSpec.getSize(widthMeasureSpec) -
                this.getPaddingLeft() - this.getPaddingRight();

    }

    private int measureHeight(int heightMeasureSpec) {
        return MeasureSpec.getSize(heightMeasureSpec) -
                this.getPaddingTop() - this.getPaddingBottom();

    }

    private float interpolateX(double x) {
        return (float) (x / row * measuredWidth);
    }

    private float interpolateY(double y) {
        return (float) (y / col * measuredHeight);
    }

}