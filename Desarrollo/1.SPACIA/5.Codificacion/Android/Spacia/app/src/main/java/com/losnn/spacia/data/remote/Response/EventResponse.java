package com.losnn.spacia.data.remote.response;

import java.util.List;

public class EventResponse {
    private int event_id;
    private String name;
    private Room room;
    private String date;
    private String hour_from;
    private String hour_to;
    private int num_attendants;
    private int num_resources;
    private List<Attendant> attendants;
    private List<Resource> resources;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour_from() {
        return hour_from;
    }

    public void setHour_from(String hour_from) {
        this.hour_from = hour_from;
    }

    public String getHour_to() {
        return hour_to;
    }

    public void setHour_to(String hour_to) {
        this.hour_to = hour_to;
    }

    public int getNum_attendants() {
        return num_attendants;
    }

    public void setNum_attendants(int num_attendants) {
        this.num_attendants = num_attendants;
    }

    public int getNum_resources() {
        return num_resources;
    }

    public void setNum_resources(int num_resources) {
        this.num_resources = num_resources;
    }

    public List<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(List<Attendant> attendants) {
        this.attendants = attendants;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    private class Room {
        private int room_id;
        private String name;
        private int capacity;


        public int getRoom_id() {
            return room_id;
        }

        public void setRoom_id(int room_id) {
            this.room_id = room_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
    }

    private class Attendant {
        private int user_id;
        private String first_name;
        private String last_name;
        private String email;


        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    private class Resource {
        private int resource_id;
        private String name;


        public int getResource_id() {
            return resource_id;
        }

        public void setResource_id(int resource_id) {
            this.resource_id = resource_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
