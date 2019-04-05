package entities;

public class Meaning {

    private String definition;
    private boolean tag_big;
    private boolean tag_small;
    // TODO: insert tags


    public Meaning(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public boolean isTag_big() {
        return tag_big;
    }

    public void setTag_big(boolean tag_big) {
        this.tag_big = tag_big;
    }

    public boolean isTag_small() {
        return tag_small;
    }

    public void setTag_small(boolean tag_small) {
        this.tag_small = tag_small;
    }
}
