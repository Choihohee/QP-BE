package inf.questpartner.dto.room;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.RoomHashTag;
import inf.questpartner.domain.room.common.tag.TagOption;
import inf.questpartner.domain.users.user.User;
import inf.questpartner.dto.users.res.ResUserPreview;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static inf.questpartner.util.constant.Constants.HOST_COUNT;

@Getter
@Setter
@NoArgsConstructor
public class ResRoomEnter {
    private Long roomId;

    private String hostEmail; // 방장 닉네임
    private String title; // 방 제목
    private int currentUsers;
    private int expectedUsers; // 인원수 제한
    private List<TagOption> roomHashTags;
    private List<ResUserPreview> users;

    @Builder
    public ResRoomEnter(Long roomId, String hostEmail, String title, int currentUsers, int expectedUsers, List<TagOption> tags, List<ResUserPreview> users) {
        this.roomId = roomId;
        this.hostEmail = hostEmail;
        this.title = title;
        this.currentUsers = currentUsers;
        this.expectedUsers = expectedUsers;
        this.roomHashTags = tags;
        this.users = users;
    }


    public static ResRoomEnter fromEntity(Room room) {

        return ResRoomEnter.builder()
                .roomId(room.getId())
                .hostEmail(room.getHostEmail())
                .title(room.getTitle())
                .expectedUsers(room.getExpectedUsers())
                .tags(toTagOption(room.getRoomHashTags()))
                .users(convertUserList(room.getParticipants()))
                .currentUsers(getUserNum(room))
                .build();
    }

    private static int getUserNum(Room room) {
        return room.getParticipants().size() + HOST_COUNT; // 총 인원수는 방장도 포함하여 센다.
    }

    private static List<TagOption> toTagOption(List<RoomHashTag> hashTags) {
        return hashTags.stream()
                .map(RoomHashTag::getTagOption)
                .collect(Collectors.toList());
    }


    public static List<ResUserPreview> convertUserList(List<User> userList) {
        return userList.stream()
                .map(ResUserPreview::convertUser)
                .collect(Collectors.toList());
    }

}

    