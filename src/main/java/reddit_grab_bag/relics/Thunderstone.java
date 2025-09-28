package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class Thunderstone extends BaseRelic{

    private static final String NAME = "Thunderstone";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private AbstractPlayer p;

    private int energy = 0;

    public Thunderstone() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
        this.p = AbstractDungeon.player;
    }

    @Override
    public void onPlayerEndTurn() {
        this.energy = EnergyPanel.getCurrentEnergy();
    }

    @Override
    public void atTurnStart() {
        if (energy > 0) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, energy), energy));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, energy), energy));
            this.addToBot(new RelicAboveCreatureAction(p, this));
        }
    }

    public void onVictory() {
        this.energy = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Thunderstone();
    }

}
