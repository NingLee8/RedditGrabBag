package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.actions.HandIsFullAction;

@SpirePatch(clz= AbstractPlayer.class, method="createHandIsFullDialog")
public class HandIsFullActionPatch {

    public static void Prefix() {
        AbstractDungeon.actionManager.addToBottom(new HandIsFullAction());
    }

}