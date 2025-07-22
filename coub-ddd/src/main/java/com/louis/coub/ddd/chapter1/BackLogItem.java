package com.louis.coub.ddd.chapter1;

import com.louis.coub.ddd.common.Entity;

public class BackLogItem extends Entity {

    private SprintId sprintId;

    private BackLogItemStatusType status;



    public boolean isScheduledForRelease(){
        return false;
    }
    public boolean isCommittedToSprint() {
        return sprintId != null;
    }

    public void setSprintId(SprintId sprintId) {
        this.sprintId = sprintId;
    }

    public void setStatus(BackLogItemStatusType status) {
        this.status = status;
    }

    public void commitTo(SprintId sprintId) {
        if (!this.isScheduledForRelease()) {
            throw new IllegalStateException("Must be scheduled for release before committing to a sprint");
        }
//        if (this.isCommittedToSprint()) {
//           if (!aSprint.sprintId.equals(sprintId)) {
//               this.uncommitFromSprint();
//            }
//        }
//        this.elevateStatusWith(BackLogItemStatusType.COMMITTED);
//        this.setSprintId(sprintId);
//        DomainEventPublisher
//                .instance()
//                .publish(new BackLogItemCommitted(this.tenant(), this.backlogItemId(), this.sprintId()));
    }

    private void elevateStatusWith(BackLogItemStatusType backLogItemStatusType) {

    }

    private void uncommitFromSprint() {

    }

}
