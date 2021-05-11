package collection;

public interface SGStringShower {
    static String toStrView(StudyGroup st) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("StudyGroup{id=" + st.getId() +
                ", name='" + st.getName() +
                "', coordinates=Coordinates{x=" + st.getCoordinates().getX() +
                ", y=" + st.getCoordinates().getY() +
                "}, creationDate=" + st.getCreationDate().toString() +
                ", studentsCount=" + st.getStudentsCount() +
                ", transferredStudents=" + st.getTransferredStudents() +
                ", averageMark=" + st.getAverageMark() +
                ", formOfEducation=" + st.getFormOfEducation() +
                ", groupAdmin=Person{name='" + st.getGroupAdmin().getName() +
                "', birthday=" + st.getGroupAdmin().getBirthday().toString() +
                ", height=" + st.getGroupAdmin().getHeight() +
                ", nationality ='" + st.getGroupAdmin().getNationality() +
                "', location=Location{x=" + st.getGroupAdmin().getLocation().getX() +
                ", y=" + st.getGroupAdmin().getLocation().getY() +
                ", z=" + st.getGroupAdmin().getLocation().getZ() +
                ", name='" + st.getGroupAdmin().getLocation().getName() +
                "'}}}\n");
        return stringBuilder.toString();
    }
}