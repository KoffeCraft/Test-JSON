package com.lusia.zegarszachowy;

import android.os.CountDownTimer;
import android.widget.Button;

public class Gracz {
    private int ileSekund;
    private boolean czyAktywny;
    private CountDownTimer countDownTimer;
    private Button button;

    public void dodajCzas(){
        ileSekund = ileSekund+10;
    }
    public Gracz(boolean czyAktywny, Button button) {
        this.czyAktywny = czyAktywny;
        this.button = button;
        ileSekund = 180;
        if(czyAktywny) {
            uruchomZegar();
        }
    }
    public void odwrocAktywnosc(){
        czyAktywny = !czyAktywny;
        if(czyAktywny){
            uruchomZegar();
            button.setEnabled(true);
        }
        else {
            zatrzymajZegar();
            button.setEnabled(false);
        }
    }
    private String wyswietlCzas(int ile){
        int sekundy = ile%60;
        int minuty = (ile/60)%60;
        return String.format("%02d:%02d", minuty, sekundy);
    }
    private void uruchomZegar(){
        countDownTimer = new CountDownTimer(ileSekund*1000, 1000) {
            @Override
            public void onFinish() {
                button.setText("Przegrałeś");
            }

            @Override
            public void onTick(long l) {
                ileSekund = (int)l/1000;
                button.setText(wyswietlCzas(ileSekund));
            }
        };
        countDownTimer.start();
    }
    private void zatrzymajZegar(){
        countDownTimer.cancel();
    }
}
