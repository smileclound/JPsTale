/*
 Copyright (c) 2013-2014, Stephen Gold
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 * Stephen Gold's name may not be used to endorse or promote products
 derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEPHEN GOLD BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jme3utilities.sky;

import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;

/**
 * Component of SkyControl to keep track of all the lights, shadows, and
 * viewports updated by the control. It also keeps track of the values applied
 * during the most recent update.
 *
 * @author Stephen Gold <sgold@sonic.net>
 */
public class Updater {
    // *************************************************************************
    // fields
    /**
     * which ambient light to update (or null for none)
     */
    private AmbientLight ambientLight = null;
    /**
     * viewports whose background colors are updated by the control - not
     * serialized
     */
    private ViewPort viewPort = null;
    /**
     * most recent color for ambient light (or null if not updated yet)
     */
    private ColorRGBA ambientColor = null;
    /**
     * most recent color for viewport background (or null if not updated yet)
     */
    private ColorRGBA backgroundColor = null;
    /**
     * most recent color for main directional light (or null if not updated yet)
     */
    private ColorRGBA mainColor = null;
    /**
     * which directional light to update (or null for none)
     */
    private DirectionalLight mainLight = null;
    /**
     * multiplier when applying the ambient light color (1 &rarr; default)
     */
    private float ambientMultiplier = 1f;
    /**
     * multiplier when applying the main light color (1 &rarr; default)
     */
    private float mainMultiplier = 1f;
    /**
     * most recent direction for main directional light (length=1, or null if
     * not updated yet)
     */
    private Vector3f direction = null;
    // *************************************************************************
    // new methods exposed

    /**
     * Copy the most recent color for ambient light.
     *
     * @return new instance (or null if not updated yet)
     */
    public ColorRGBA getAmbientColor() {
        if (ambientColor == null) {
            return null;
        }
        return ambientColor.clone();
    }

    /**
     * Copy the most recent color for the viewport background.
     *
     * @return new instance (or null if not updated yet)
     */
    public ColorRGBA getBackgroundColor() {
        if (backgroundColor == null) {
            return null;
        }
        return backgroundColor.clone();
    }

    /**
     * Copy the most recent direction for the main directional light.
     *
     * @return new instance (or null if not updated yet)
     */
    public Vector3f getDirection() {
        if (direction == null) {
            return null;
        }
        return direction.clone();
    }

    /**
     * Copy the most recent color for the main directional light.
     *
     * @return new instance (or null if not updated yet)
     */
    public ColorRGBA getMainColor() {
        if (mainColor == null) {
            return null;
        }
        return mainColor.clone();
    }

    /**
     * Save a reference to the scene's ambient light. As long as the reference
     * has a non-null value, the control will continuously update the light's
     * color and intensity.
     *
     * @param ambientLight
     *            the scene's main ambient light (or null for none)
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * Alter the multiplier for the ambient light intensity.
     *
     * @param factor
     *            (&ge;0, 1 &rarr; default)
     */
    public void setAmbientMultiplier(float factor) {
        ambientMultiplier = factor;
    }

    /**
     * Save a reference to the scene's main directional light. As long as the
     * reference has a non-null value, the control will continuously update the
     * light's color, direction, and intensity.
     *
     * @param mainLight
     *            the scene's main directional light (or null for none)
     */
    public void setMainLight(DirectionalLight mainLight) {
        this.mainLight = mainLight;
    }

    /**
     * Alter the multiplier for the main light intensity.
     *
     * @param factor
     *            (&ge;0, 1 &rarr; default)
     */
    public void setMainMultiplier(float factor) {
        mainMultiplier = factor;
    }

    /**
     * Add a viewport to the list of viewports whose background colors are
     * updated by the control. Note that the list is not serialized.
     *
     * @param viewPort
     *            (not null)
     */
    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

    /**
     * Update all the lights, shadows, and viewports.
     *
     * @param ambientColor
     *            color and intensity of ambient light (not null, unaffected)
     * @param backgroundColor
     *            color of viewport backgrounds (not null, unaffected)
     * @param mainColor
     *            color and intensity of the main directional light (not null,
     *            unaffected)
     * @param bloomIntensity
     *            intensity of bloom effect (&ge;0)
     * @param shadowIntensity
     *            intensity of shadows (&lt;1, &ge;0)
     * @param direction
     *            direction to the main light source (length=1, unaffected)
     */
    void update(ColorRGBA ambientColor, ColorRGBA backgroundColor, ColorRGBA mainColor, Vector3f direction) {
        assert ambientColor != null;
        assert backgroundColor != null;
        assert mainColor != null;
        assert direction != null;
        assert direction.isUnitVector() : direction;
        /*
         * Copy new values to the corresponding "most recent" fields.
         */
        if (this.ambientColor == null) {
            this.ambientColor = ambientColor.clone();
        } else {
            this.ambientColor.set(ambientColor);
        }
        if (this.backgroundColor == null) {
            this.backgroundColor = backgroundColor.clone();
        } else {
            this.backgroundColor.set(backgroundColor);
        }
        if (this.mainColor == null) {
            this.mainColor = mainColor.clone();
        } else {
            this.mainColor.set(mainColor);
        }
        if (this.direction == null) {
            this.direction = direction.clone();
        } else {
            this.direction.set(direction);
        }

        if (mainLight != null) {
            ColorRGBA color = ambientColor.mult(mainMultiplier);
            mainLight.setColor(color);
            /*
             * The direction of the main light is the direction in which it
             * propagates, which is the opposite of the direction to the light
             * source.
             */
            Vector3f propagationDirection = direction.negate();
            mainLight.setDirection(propagationDirection);
        }
        if (ambientLight != null) {
            ColorRGBA color = ambientColor.mult(ambientMultiplier);
            ambientLight.setColor(color);
        }
        if (viewPort != null) {
            viewPort.setBackgroundColor(backgroundColor);
        }
    }
}