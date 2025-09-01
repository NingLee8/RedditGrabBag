package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;

import java.util.HashMap;
import java.util.Map;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class ReversePrism extends BaseRelic {

    private static final String NAME = "ReversePrism";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;

    private Map<String, Boolean> evokeMap;

    public ReversePrism() {
        super(ID, NAME, AbstractCard.CardColor.BLUE, RARITY, SOUND);
        evokeMap = new HashMap<>();
    }

    public void atBattleStart() {
        evokeMap = new HashMap<>();
    }

    @Override
    public void onEvokeOrb(AbstractOrb ammo) {
        if (!evokeMap.containsKey(ammo.ID)) {
            evokeMap.put(ammo.ID, true);
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, 1), 1));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.description = getUpdatedDescription();
        }

    }

    @Override
    public void onVictory() {
        evokeMap = new HashMap<>();
        this.description = getUpdatedDescription();
    }

    @Override
    public String getUpdatedDescription() {
        if (evokeMap == null || evokeMap.isEmpty()) {
            return DESCRIPTIONS[0];
        } else {
            return DESCRIPTIONS[0] + DESCRIPTIONS[1] + evokeMap.keySet();
        }
    }
}
