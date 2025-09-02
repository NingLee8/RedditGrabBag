package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.actions.LimitEnergyAction;
import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class OverflowingChalice extends BaseRelic {

    private static final String NAME = "OverflowingChalice";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final int ENERGY_LIMIT = 4;
    public OverflowingChalice() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onEndOfStartTurn(){
        this.limitEnergy();
    }

    @Override
    public void onAfterPotionUse(){
        this.limitEnergy();
    }

    @Override
    public void onAfterUseCard(){
        this.limitEnergy();
    }

    public void limitEnergy() {
        this.addToBot(new LimitEnergyAction(ENERGY_LIMIT, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new OverflowingChalice();
    }

}
