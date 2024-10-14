package org.chatClient.face;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
class RequestCorrespondence {
    private String type;
    private String period;
    private String collocutor;
}
