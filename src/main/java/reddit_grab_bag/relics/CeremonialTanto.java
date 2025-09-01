package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class CeremonialTanto extends BaseRelic{

    private static final String NAME = "CeremonialTanto";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.MAGICAL;
    private static AbstractPlayer p;

    public CeremonialTanto() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
        p = AbstractDungeon.player;
    }

    @Override
    public void atTurnStart() {
        this.addToBot(new LoseHPAction(p, p, 1));
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new CeremonialTanto();
    }
}
