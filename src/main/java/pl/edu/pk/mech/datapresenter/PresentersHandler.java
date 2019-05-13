package pl.edu.pk.mech.datapresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PresentersHandler {

    private List<Presenter> presenters = new ArrayList<>();

    public void addPresenter(Presenter presenter) {
        presenters.add(presenter);
    }

    public void removePresenter(Presenter presenter) {
        presenters.remove(presenter);
    }

    public void update(Map<String, String> data) {
        presenters.forEach(presenter -> presenter.update(data));
    }
}
