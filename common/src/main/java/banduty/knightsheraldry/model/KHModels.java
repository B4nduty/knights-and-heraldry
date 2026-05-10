package banduty.knightsheraldry.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;

public class KHModels {
    public static HumanoidModel<LivingEntity> getArmModel() {
        return new AccessoryArmModel(AccessoryArmModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getBootsModel() {
        return new AccessoryBootsModel(AccessoryBootsModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getChestplateModel() {
        return new AccessoryChestplateModel(AccessoryChestplateModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getHelmetModel() {
        return new AccessoryHelmetModel(AccessoryHelmetModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getLeggingsModel() {
        return new AccessoryLeggingsModel(AccessoryLeggingsModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getOpenHelmetModel() {
        return new AccessoryOpenHelmetModel(AccessoryOpenHelmetModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getBevorModel() {
        return new BevorModel(BevorModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getCageHelmClosedModel() {
        return new CageHelmClosed(CageHelmClosed.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getCageHelmOpenedModel() {
        return new CageHelmOpened(CageHelmOpened.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getChaperonModel() {
        return new ChaperonModel(ChaperonModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getCloakHoodModel() {
        return new CloakHoodModel(CloakHoodModel.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getCloseHelmClosedModel() {
        return new CloseHelmClosed(CloseHelmClosed.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getCloseHelmOpenedModel() {
        return new CloseHelmOpened(CloseHelmOpened.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getGreatHelmModel() {
        return new GreatHelm(GreatHelm.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getSavoyardClosedModel() {
        return new SavoyardClosed(SavoyardClosed.getTexturedModelData().bakeRoot());
    }
    public static HumanoidModel<LivingEntity> getSavoyardOpenedModel() {
        return new SavoyardOpened(SavoyardOpened.getTexturedModelData().bakeRoot());
    }
}
