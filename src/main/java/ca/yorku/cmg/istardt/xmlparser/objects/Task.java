package ca.yorku.cmg.istardt.xmlparser.objects;

import ca.yorku.cmg.istardt.xmlparser.xml.deserializers.TaskDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = TaskDeserializer.class)
public class Task extends DecompositionElement {

    @JacksonXmlElementWrapper(localName = "effectGroup")
    @JacksonXmlProperty(localName = "effect")
    @JsonManagedReference("task-effects")
    private List<Effect> effects;

    @JsonBackReference("actor-tasks")
    private Actor actor;

    public Task() {
        this.effects = new ArrayList<>();
        this.setDecompType(DecompType.TERM); // Set default DecompType to TERM
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;

        // Set up the relationship between task and effects
        for (Effect effect : effects) {
            effect.setTask(this);
        }
    }

    public void addEffect(Effect e) {
        if (effects == null) {
            effects = new ArrayList<>();
        }
        effects.add(e);
        e.setTask(this);
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    /**
     * A task is deterministic if it has only one effect with 100% probability.
     *
     * @return True if the task is deterministic, false otherwise
     */
    @JsonIgnore
    public boolean isDeterministic() {
        return effects != null && effects.size() == 1 && effects.get(0).getProbability() == 1.0f;
    }

    @Override
    public String toString() {
        return "Task{id=" + getId() +
                ", effectCount=" + (effects != null ? effects.size() : 0) +
                ", deterministic=" + isDeterministic() +
                ", decompType=" + getDecompType() +
                ", childCount=" + (getChildren() != null ? getChildren().size() : 0) +
                ", hasParent=" + (getParent() != null) + "}";
    }
}