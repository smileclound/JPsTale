package org.pstale.gui;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.texture.Texture;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Button.ButtonAction;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Insets3f;
import com.simsilica.lemur.component.IconComponent;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import com.simsilica.lemur.component.TbtQuadBackgroundComponent;
import com.simsilica.lemur.core.CommandMap;
import com.simsilica.lemur.style.Attributes;
import com.simsilica.lemur.style.Styles;

public class Style {

    static AssetManager assetManager;

    static GuiGlobals gui;
    static Styles styles;

    public static void initStyle(Application app) {
        assetManager = app.getAssetManager();

        GuiGlobals.initialize(app);

        gui = GuiGlobals.getInstance();

        styles = gui.getStyles();

        styles.setDefault(font("Font/field.fnt"));
        styles.setDefaultStyle("glass");

        TbtQuadBackgroundComponent gradient = TbtQuadBackgroundComponent.create(
                texture("/com/simsilica/lemur/icons/bordered-gradient.png", false), 1, 1, 1, 126, 126, 1f, false);

        TbtQuadBackgroundComponent bevel = TbtQuadBackgroundComponent
                .create(texture("/com/simsilica/lemur/icons/bevel-quad.png", false), 0.125f, 8, 8, 119, 119, 1f, false);

        TbtQuadBackgroundComponent border = TbtQuadBackgroundComponent
                .create(texture("/com/simsilica/lemur/icons/border.png", false), 1, 1, 1, 6, 6, 1f, false);

        TbtQuadBackgroundComponent border2 = TbtQuadBackgroundComponent
                .create(texture("/com/simsilica/lemur/icons/border.png", false), 1, 2, 2, 6, 6, 1f, false);

        QuadBackgroundComponent doubleGradient = new QuadBackgroundComponent(new ColorRGBA(0.5f, 0.75f, 0.85f, 0.5f));
        doubleGradient.setTexture(texture("/com/simsilica/lemur/icons/double-gradient-128.png", false));

        Attributes attr;
        attr = styles.getSelector("glass");
        attr.set("fontSize", 14);

        attr = styles.getSelector("label", "glass");
        attr.set("insets", new Insets3f(2, 2, 0, 2));
        attr.set("color", new ColorRGBA(0.5f, 0.75f, 0.75f, 0.85f));

        attr = styles.getSelector("container", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
        }

        attr = styles.getSelector("slider", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
        }

        Command<Button> pressedCommand = new Command<Button>() {
            public void execute(Button source) {
                if (source.isPressed()) {
                    source.move(1, -1, 0);
                } else {
                    source.move(-1, 1, 0);
                }
            }
        };

        CommandMap stdButtonCommands = new CommandMap(null);
        stdButtonCommands.addCommands(ButtonAction.Down, pressedCommand);
        stdButtonCommands.addCommands(ButtonAction.Up, pressedCommand);

        attr = styles.getSelector("title", "glass");
        {
            attr.set("color", color(0.8, 0.9, 1, 0.85f));
            attr.set("highlightColor", color(1, 0.8, 1, 0.85f));
            attr.set("shadowColor", color(0, 0, 0, 0.75f));
            attr.set("shadowOffset", new Vector3f(2, -2, -1));
            QuadBackgroundComponent background = new QuadBackgroundComponent(color(0.5, 0.75, 0.85, 0.5));
            background.setTexture(texture("/com/simsilica/lemur/icons/double-gradient-128.png", false));
            attr.set("background", background);
            attr.set("insets", new Insets3f(2, 2, 2, 2));
            attr.set("buttonCommands", stdButtonCommands);
        }

        attr = styles.getSelector("button", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0, 0.75, 0.75, 0.5));
            attr.set("color", color(0.8, 0.9, 1, 0.85f));
            attr.set("insets", new Insets3f(2, 2, 2, 2));
            attr.set("background", background);
            attr.set("buttonCommands", stdButtonCommands);
        }

        attr = styles.getSelector("slider", "glass");
        {
            attr.set("insets", new Insets3f(1, 3, 1, 2));
        }

        attr = styles.getSelector("slider", "button", "glass");
        {
            QuadBackgroundComponent background = doubleGradient.clone();
            background.setColor(color(0.5, 0.75, 0.75, 0.5));
            attr.set("background", background);
            attr.set("insets", new Insets3f(0, 0, 0, 0));
        }

        attr = styles.getSelector("slider.thumb.button", "glass");
        {
            attr.set("text", "[]");
            attr.set("color", color(0.6, 0.8, 0.8, 0.85));
        }

        attr = styles.getSelector("slider.left.button", "glass");
        {
            attr.set("text", "-");
            QuadBackgroundComponent background = doubleGradient.clone();
            background.setColor(color(0.5, 0.75, 0.75, 0.5));
            background.setMargin(5, 0);
            attr.set("background", background);
            attr.set("color", color(0.6, 0.8, 0.8, 0.85));
        }

        attr = styles.getSelector("slider.right.button", "glass");
        {
            attr.set("text", "+");
            QuadBackgroundComponent background = doubleGradient.clone();
            background.setColor(color(0.5, 0.75, 0.75, 0.5));
            background.setMargin(4, 0);
            attr.set("background", background);
            attr.set("color", color(0.6, 0.8, 0.8, 0.85));
        }

        attr = styles.getSelector("checkbox", "glass");
        {
            IconComponent on = new IconComponent("/com/simsilica/lemur/icons/Glass-check-on.png", 1f, 0, 0, 1f, false);
            on.setColor(color(0.5, 0.9, 0.9, 0.9));
            on.setMargin(5, 0);
            IconComponent off = new IconComponent("/com/simsilica/lemur/icons/Glass-check-off.png", 1f, 0, 0, 1f,
                    false);
            off.setColor(color(0.6, 0.8, 0.8, 0.8));
            off.setMargin(5, 0);

            attr.set("onView", on);
            attr.set("offView", off);
            attr.set("color", color(0.8, 0.9, 1, 0.85f));
        }

        attr = styles.getSelector("rollup", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
        }

        attr = styles.getSelector("tabbedPanel", "glass");
        {
            attr.set("activationColor", color(0.8, 0.9, 1, 0.85f));
        }

        attr = styles.getSelector("tabbedPanel.container", "glass");
        {
            attr.set("background", null);
        }

        attr = styles.getSelector("tab.button", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
            attr.set("color", color(0.4, 0.45, 0.5, 0.85f));
            attr.set("insets", new Insets3f(4, 2, 0, 2));
            attr.set("buttonCommands", stdButtonCommands);
        }

        // proto styles
        QuadBackgroundComponent transparent = new QuadBackgroundComponent(color(0, 0, 0, 0));

        attr = styles.getSelector("optionPanel", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
        }

        attr = styles.getSelector("optionPanel.container", "glass");
        {

            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(new ColorRGBA(0.25f, 0.4f, 0.6f, 0.25f));
            background.setMargin(10, 10);
            attr.set("background", background);
            attr.set("insets", new Insets3f(2, 2, 2, 2));
        }

        attr = styles.getSelector("title.label", "glass");
        {
            attr.set("color", color(0.8, 0.9, 1, 0.85f));
            attr.set("highlightColor", color(1, 0.8, 1, 0.85f));
            attr.set("shadowColor", color(0, 0, 0, 0.75f));
            attr.set("shadowOffset", new Vector3f(2, -2, -1));
            QuadBackgroundComponent background = new QuadBackgroundComponent(color(0.5, 0.75, 0.85, 0.5));
            background.setTexture(texture("/com/simsilica/lemur/icons/double-gradient-128.png", false));
            attr.set("background", background);
            attr.set("insets", new Insets3f(2, 2, 2, 2));
        }

        attr = styles.getSelector("list.container", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
            attr.set("insets", new Insets3f(2, 2, 2, 2, 2, 2));
        }

        attr = styles.getSelector("list.item", "glass");
        {
            attr.set("color", color(0.5, 0.75, 0.75, 0.85));
            attr.set("background", transparent);
        }

        attr = styles.getSelector("list.selector", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            background.setColor(color(0.4, 0.6, 0.6, 0.5));
            background.getMaterial().getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.AlphaAdditive);
            attr.set("background", background);
        }

        attr = styles.getSelector("colorChooser.value", "glass");
        {
            TbtQuadBackgroundComponent background = gradient.clone();
            border.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", background);
            attr.set("insets", new Insets3f(2, 2, 2, 2, 2, 2));
        }

        attr = styles.getSelector("colorChooser.colors", "glass");
        {
            TbtQuadBackgroundComponent _border = gradient.clone();
            _border.setColor(color(0.25, 0.5, 0.5, 0.5));
            attr.set("background", _border);
            attr.set("insets", new Insets3f(2, 2, 2, 2, 2, 2));
        }
    }

    static BitmapFont font(String name) {
        return gui.loadFont(name);
    }

    static ColorRGBA color(double r, double g, double b, double a) {
        return new ColorRGBA((float) r, (float) g, (float) b, (float) a);
    }

    static Texture texture(String name) {
        return gui.loadTexture(name, true, true);
    }

    static Texture texture(String name, boolean generateMips) {
        if (name == null) {
            throw new IllegalArgumentException("Texture name not specified.");
        }

        Texture t = gui.loadTexture(name, true, generateMips);

        return t;
    }

    static Vector3f vec3(Number x, Number y, Number z) {
        return new Vector3f(x.floatValue(), y.floatValue(), z.floatValue());
    }

    static Vector2f vec2(Number x, Number y) {
        return new Vector2f(x.floatValue(), y.floatValue());
    }

}
