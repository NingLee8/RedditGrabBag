package reddit_grab_bag.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class FrequencyConverterPower extends BasePower {

    public static final String POWER_ID = makeID("FrequencyConverterPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;

    public FrequencyConverterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, FrequencyConverterPower.POWER_ID));
        }
    }


    public void onSpecificTrigger() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, FrequencyConverterPower.POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
