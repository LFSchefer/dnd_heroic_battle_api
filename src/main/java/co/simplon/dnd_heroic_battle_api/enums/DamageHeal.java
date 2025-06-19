package co.simplon.dnd_heroic_battle_api.enums;

public enum DamageHeal {
    DAMAGE,
    HEAL;

    public static boolean isDamage(String input) {
        return input.equalsIgnoreCase("DAMAGE");
    }

    public static boolean isHeal(String input) {
        return input.equalsIgnoreCase("HEAL");
    }
}
