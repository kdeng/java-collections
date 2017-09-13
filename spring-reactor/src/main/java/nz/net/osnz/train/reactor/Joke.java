package nz.net.osnz.train.reactor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kdeng on 7/03/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {

    int id;
    String joke;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getJoke() {
        return joke;
    }
    public void setJoke(String joke) {
        this.joke = joke;
    }

}
