package reddit_grab_bag.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class AnticipationPower extends BasePower{

    public static final String POWER_ID = makeID("AnticipationPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;

    public AnticipationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void atStartOfTurn (){
        this.addToBot(new VFXAction(owner, new FlameBarrierEffect(owner.hb.cX, owner.hb.cY), Settings.FAST_MODE ? 0.1F : 0.5F));
        this.addToBot(new ApplyPowerAction(owner, owner, new VigorPower(owner, this.amount), this.amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
