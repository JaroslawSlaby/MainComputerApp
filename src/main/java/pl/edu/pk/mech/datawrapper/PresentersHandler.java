package pl.edu.pk.mech.datawrapper;

import java.util.ArrayList;
import java.util.List;

public class PresentersHandler {

    private List<Presenter> presenters = new ArrayList<>();

    public void addPresenter(Presenter presenter) {
        presenters.add(presenter);
    }

    public void removePresenter(Presenter presenter) {
        presenters.remove(presenter);
    }

    public void update(String data) {
        presenters.forEach(presenter -> presenter.update(data));
    }
}
