package reddit_grab_bag.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class PerfectBalancePower extends BasePower{

    public static final String POWER_ID = makeID("PerfectBalancePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;

    public PerfectBalancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    public void onSpecificTrigger() {
        this.flash();
        this.addToBot(new GainBlockAction(this.owner, this.amount, Settings.FAST_MODE));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
