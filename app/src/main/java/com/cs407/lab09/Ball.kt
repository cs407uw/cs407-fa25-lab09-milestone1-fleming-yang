package com.cs407.lab09

import android.util.Log

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        val scale = 5f

        val accX0 = accX
        val accY0 = accY
        accX = xAcc*scale
        accY = yAcc*scale

        val newVelocityX = velocityX + 0.5f*(accX0 + accX)*dT
        val newVelocityY = velocityY + 0.5f*(accY0 + accY)*dT

        posX += velocityX*(dT) + (dT*dT/6f)*(3*accX0 + accX)
        posY += velocityY*(dT) + (dT*dT/6f)*(3*accY0 + accY)

        velocityX = newVelocityX
        velocityY = newVelocityY

        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        if (posX > backgroundWidth) {
            posX = backgroundWidth + ballSize
            accX = 0f
            velocityX= 0f
        }

        if (posX < 0f + ballSize) {
            posX = backgroundWidth + ballSize
            accX = 0f
            velocityX= 0f
        }

        if (posY > backgroundHeight - ballSize) {
            posX = posY - ballSize
            accY = 0f
            velocityY = 0f
        }

        if (posY < 0f + ballSize) {
            posY = 0f + ballSize
            accY = 0f
            velocityY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth/2f) - ballSize
        posY = (backgroundHeight/2f) - ballSize
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}