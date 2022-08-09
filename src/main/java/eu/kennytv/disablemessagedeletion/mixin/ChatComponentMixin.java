package eu.kennytv.disablemessagedeletion.mixin;

import eu.kennytv.disablemessagedeletion.DisableMessageDeletionMod;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {

    private static final int DELETION_INDICATOR_COLOR = 255 | (150 << 8) | (200 << 16);
    private static final GuiMessageTag DELETION_ATTEMPT = new GuiMessageTag(DELETION_INDICATOR_COLOR, null, null, "Deletion attempt");

    @Shadow
    @Final
    private Minecraft minecraft;
    @Shadow
    @Final
    private List<GuiMessage> allMessages;

    @Shadow
    protected abstract void refreshTrimmedMessage();

    @Inject(at = @At("HEAD"), method = "deleteMessage", cancellable = true)
    public void tick(final MessageSignature messageSignature, final CallbackInfo ci) {
        ci.cancel();
        for (int i = 0; i < this.allMessages.size(); i++) {
            final GuiMessage guiMessage = this.allMessages.get(i);
            final MessageSignature signature = guiMessage.headerSignature();
            if (signature != null && signature.equals(messageSignature)) {
                if (DisableMessageDeletionMod.minDeletionDelayTicks() != -1 && this.minecraft.gui.getGuiTicks() - guiMessage.addedTime() >= DisableMessageDeletionMod.minDeletionDelayTicks()) {
                    // Remove message as per usual
                    this.allMessages.remove(i);
                } else {
                    // Replace message with new tag
                    this.allMessages.set(i, new GuiMessage(guiMessage.addedTime(), guiMessage.content(), guiMessage.headerSignature(), DELETION_ATTEMPT));
                }
                this.refreshTrimmedMessage();
                return;
            }
        }
    }
}
