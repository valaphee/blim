/*
 * Copyright (c) 2021-2022, Valaphee.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.valaphee.netcode.mcbe.world

/**
 * @author Kevin Ludwig
 */
enum class SoundEvent {
    ItemUseOn,
    Hit,
    Step,
    Fly,
    Jump,
    Break,
    Place,
    HeavyStep,
    Gallop,
    Fall,
    Ambient,
    AmbientBaby,
    AmbientInWater,
    Breath,
    Death,
    DeathInWater,
    DeathToZombie,
    Hurt,
    HurtInWater,
    Mad,
    Boost,
    Bow,
    SquishBig,
    SquishSmall,
    FallBig,
    FallSmall,
    Splash,
    Fizz,
    Flap,
    Swim,
    Drink,
    Eat,
    TakeOff,
    Shake,
    Plop,
    Land,
    Saddle,
    Armor,
    MobArmorStandPlace,
    AddChest,
    Throw,
    Attack,
    AttackNoDamage,
    AttackStrong,
    Warn,
    Shear,
    Milk,
    Thunder,
    Explode,
    Fire,
    Ignite,
    Fuse,
    Stare,
    Spawn,
    Shoot,
    BreakBlock,
    Launch,
    Blast,
    LargeBlast,
    Twinkle,
    Remedy,
    Unfect,
    LevelUp,
    BowHit,
    BulletHit,
    ExtinguishFire,
    ItemFizz,
    ChestOpen,
    ChestClosed,
    ShulkerBoxOpen,
    ShulkerBoxClosed,
    EnderChestOpen,
    EnderChestClosed,
    PowerOn,
    PowerOff,
    Attach,
    Detach,
    Deny,
    Tripod,
    Pop,
    DropSlot,
    Note,
    Thorns,
    PistonIn,
    PistonOut,
    Portal,
    Water,
    LavaPop,
    Lava,
    Burp,
    BucketFillWater,
    BucketFillLava,
    BucketEmptyWater,
    BucketEmptyLava,
    ArmorEquipChain,
    ArmorEquipDiamond,
    ArmorEquipGeneric,
    ArmorEquipGold,
    ArmorEquipIron,
    ArmorEquipLeather,
    ArmorEquipElytra,
    Record13,
    RecordCat,
    RecordBlocks,
    RecordChirp,
    RecordFar,
    RecordMall,
    RecordMellohi,
    RecordStal,
    RecordStrad,
    RecordWard,
    Record11,
    RecordWait,
    StopRecord,
    Flop,
    ElderGuardianCurse,
    MobWarning,
    MobWarningBaby,
    Teleport,
    ShulkerOpen,
    ShulkerClose,
    Haggle,
    HaggleYes,
    HaggleNo,
    HaggleIdle,
    ChorusGrow,
    ChorusDeath,
    Glass,
    PotionBrewed,
    CastSpell,
    PrepareAttack,
    PrepareSummon,
    PrepareWololo,
    Fang,
    Charge,
    CameraTakePicture,
    LeashKnotPlace,
    LeashKnotBreak,
    Growl,
    Whine,
    Pant,
    Purr,
    Purreow,
    DeathMinVolume,
    DeathMidVolume,
    ImitateBlaze,
    ImitateCaveSpider,
    ImitateCreeper,
    ImitateElderGuardian,
    ImitateEnderDragon,
    ImitateEnderman,
    Unknown151,
    ImitateEvocationIllager,
    ImitateGhast,
    ImitateHusk,
    ImitateIllusionIllager,
    ImitateMagmaCube,
    ImitatePolarBear,
    ImitateShulker,
    ImitateSilverfish,
    ImitateSkeleton,
    ImitateSlime,
    ImitateSpider,
    ImitateStray,
    ImitateVex,
    ImitateVindicationIllager,
    ImitateWitch,
    ImitateWither,
    ImitateWitherSkeleton,
    ImitateWolf,
    ImitateZombie,
    ImitateZombiePigman,
    ImitateZombieVillager,
    BlockEndPortalFrameFill,
    BlockEndPortalSpawn,
    RandomAnvilUse,
    BottleDragonBreath,
    PortalTravel,
    ItemTridentHit,
    ItemTridentReturn,
    ItemTridentRiptide1,
    ItemTridentRiptide2,
    ItemTridentRiptide3,
    ItemTridentThrow,
    ItemTridentThunder,
    ItemTridentHitGround,
    Default,
    BlockFletchingTableUse,
    ElementConstructorOpen,
    IceBombHit,
    BalloonPop,
    LabTableReactionIceBomb,
    LabTableReactionBleach,
    LabTableReactionEpaste,
    LabTableReactionEpaste2,
    Unknown195,
    Unknown196,
    Unknown197,
    Unknown198,
    LabTableReactionFertilizer,
    LabTableReactionFireball,
    LabTableReactionMagnesiumSalt,
    LabTableReactionMiscFire,
    LabTableReactionFire,
    LabTableReactionMiscExplosion,
    LabTableReactionMiscMystical,
    LabTableReactionMiscMystical2,
    LabTableReactionProduct,
    SparklerUse,
    GlowStickUse,
    SparklerActive,
    ConvertToDrowned,
    BucketFillFish,
    BucketEmptyFish,
    BubbleUp,
    BubbleDown,
    BubblePop,
    BubbleUpInside,
    BubbleDownInside,
    HurtBaby,
    DeathBaby,
    StepBaby,
    Unknown222,
    Born,
    BlockTurtleEggBreak,
    BlockTurtleEggCrack,
    BlockTurtleEggHatch,
    Unknown227,
    BlockTurtleEggAttack,
    BeaconActivate,
    BeaconAmbient,
    BeaconDeactivate,
    BeaconPower,
    ConduitActivate,
    ConduitAmbient,
    ConduitAttack,
    ConduitDeactivate,
    ConduitShort,
    Swoop,
    BlockBambooSaplingPlace,
    PreSneeze,
    Sneeze,
    AmbientTame,
    Scared,
    BlockScaffoldingClimb,
    CrossbowLoadingStart,
    CrossbowLoadingMiddle,
    CrossbowLoadingEnd,
    CrossbowShoot,
    CrossbowQuickChargeStart,
    CrossbowQuickChargeMiddle,
    CrossbowQuickChargeEnd,
    AmbientAggressive,
    AmbientWorried,
    CannotBreed,
    ItemShieldBlock,
    ItemBookPut,
    BlockGrindstoneUse,
    BlockBellHit,
    BlockCampfireCrackle,
    Roar,
    Stun,
    BlockSweetBerryBushHurt,
    BlockSweetBerryBushPick,
    BlockCartographyTableUse,
    BlockStoneCutterUse,
    BlockComposterEmpty,
    BlockComposterFill,
    BlockComposterFillSuccess,
    BlockComposterReady,
    BlockBarrelOpen,
    BlockBarrelClose,
    RaidHorn,
    BlockLoomUse,
    AmbientInRaid,
    UiCartographyTableTakeResult,
    UiStoneCutterTakeResult,
    UiLoomTakeResult,
    BlockSmokerSmoke,
    BlockBlastFurnaceFireCrackle,
    BlockSmithingTableUse,
    Screech,
    BlockFurnaceLit,
    Sleep,
    ConvertMooshroom,
    MilkSuspiciously,
    Celebrate,
    JumpPrevent,
    AmbientPollinate,
    BlockBeehiveDrip,
    BlockBeehiveEnter,
    BlockBeehiveExit,
    BlockBeehiveWork,
    BlockBeehiveShear,
    HoneyBottleDrink,
    AmbientCave,
    Retreat,
    ConvertToZombified,
    Admire,
    StepLava,
    Tempt,
    Panic,
    Angry,
    AmbientWarpedForest,
    AmbientSoulSandValley,
    AmbientNetherWastes,
    AmbientBasaltDeltas,
    AmbientCrimsonForest,
    RespawnAnchorCharge,
    RespawnAnchorDeplete,
    RespawnAnchorSetSpawn,
    RespawnAnchorAmbient,
    SoulEscapeQuiet,
    SoulEscapeLoud,
    RecordPigstep,
    LinkCompassToLodestone,
    UseSmithingTable,
    EquipNetherite,
    AmbientLoopWarpedForest,
    AmbientLoopSoulsandValley,
    AmbientLoopNetherWastes,
    AmbientLoopBasaltDeltas,
    AmbientLoopCrimsonForest,
    AmbientAdditionWarpedForest,
    AmbientAdditionSoulsandValley,
    AmbientAdditionNetherWastes,
    AmbientAdditionBasaltDeltas,
    AmbientAdditionCrimsonForest,
    SculkSensorPowerOn,
    SculkSensorPowerOff,
    BucketFillPowderSnow,
    BucketEmptyPowderSnow,
    PointedDripstoneCauldronDripLava,
    PointedDripstoneCauldronDripWater,
    PointedDripstoneDripLava,
    PointedDripstonDripWater,
    CaveVinesPickBerries,
    BigDripleafTiltDown,
    BigDripleafTiltUp,
    CopperWaxOn,
    CopperWaxOff,
    Scrape,
    PlayerHurtDrown,
    PlayerHurtOnFire,
    PlayerHurtFreeze,
    UseSpyglass,
    StopUsingSpyglass,
    AmethystBlockChime,
    AmbientScreamer,
    HurtScreamer,
    DeathScreamer,
    MilkScreamer,
    JumpToBlock,
    PreRam,
    PreRamScreamer,
    RamImpact,
    RamImpactScreamer,
    SquidInkSquirt,
    GlowSquidInkSquirt,
    ConvertToStray,
    CakeAddCandle,
    ExtinguishCandle,
    AmbientCandle,
    BlockClick,
    BlockClickFail,
    SculkCatalystBloom,
    SculkShriekerShriek,
    WardenNearbyClose,
    WardenNearbyCloser,
    WardenNearbyClosest,
    WardenSlightlyAngry,
    RecordOtherside,
    Tongue,
    CrackIronGolem,
    RepairIronGolem
}
