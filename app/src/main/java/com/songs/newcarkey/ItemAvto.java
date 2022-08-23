package com.songs.newcarkey;

public class ItemAvto {

    private String nameAvto;
    private int imgKey;
    private int imgAvtoSp;
    private int soundZapusk;
    private int soundGaz;
    private int soundFinal;

    public ItemAvto(String nameAvto, int imgKey, int imgAvtoSp, int soundZapusk, int soundGaz, int soundFinal) {
        this.nameAvto = nameAvto;
        this.imgKey = imgKey;
        this.imgAvtoSp = imgAvtoSp;
        this.soundZapusk = soundZapusk;
        this.soundGaz = soundGaz;
        this.soundFinal = soundFinal;
    }

    public String getNameAvto() {
        return nameAvto;
    }

    public void setNameAvto(String nameAvto) {
        this.nameAvto = nameAvto;
    }

    public int getImgKey() {
        return imgKey;
    }

    public void setImgKey(int imgKey) {
        this.imgKey = imgKey;
    }

    public int getImgAvtoSp() {
        return imgAvtoSp;
    }

    public void setImgAvtoSp(int imgAvtoSp) {
        this.imgAvtoSp = imgAvtoSp;
    }

    public int getSoundZapusk() {
        return soundZapusk;
    }

    public void setSoundZapusk(int soundZapusk) {
        this.soundZapusk = soundZapusk;
    }

    public int getSoundGaz() {
        return soundGaz;
    }

    public void setSoundGaz(int soundGaz) {
        this.soundGaz = soundGaz;
    }

    public int getSoundFinal() {
        return soundFinal;
    }

    public void setSoundFinal(int soundFinal) {
        this.soundFinal = soundFinal;
    }
}
