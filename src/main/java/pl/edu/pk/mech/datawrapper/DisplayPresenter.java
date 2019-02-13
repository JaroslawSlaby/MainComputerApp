package pl.edu.pk.mech.datawrapper;

public class DisplayPresenter implements Presenter {

    @Override
    public void update(String data) {
        System.out.println(data);
    }
}
