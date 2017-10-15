package com.example.hello.model.bean;

import java.util.List;

/**
 * Created by youngbear on 2017/10/15.
 */

public class UserBean {


    /**
     * total_count : 5
     * incomplete_results : false
     * items : [{"login":"cng1985","id":302495,"avatar_url":"https://avatars2.githubusercontent.com/u/302495?v=4","gravatar_id":"","url":"https://api.github.com/users/cng1985","html_url":"https://github.com/cng1985","followers_url":"https://api.github.com/users/cng1985/followers","following_url":"https://api.github.com/users/cng1985/following{/other_user}","gists_url":"https://api.github.com/users/cng1985/gists{/gist_id}","starred_url":"https://api.github.com/users/cng1985/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/cng1985/subscriptions","organizations_url":"https://api.github.com/users/cng1985/orgs","repos_url":"https://api.github.com/users/cng1985/repos","events_url":"https://api.github.com/users/cng1985/events{/privacy}","received_events_url":"https://api.github.com/users/cng1985/received_events","type":"User","site_admin":false,"score":15.201826},{"login":"YoungBear","id":8236175,"avatar_url":"https://avatars1.githubusercontent.com/u/8236175?v=4","gravatar_id":"","url":"https://api.github.com/users/YoungBear","html_url":"https://github.com/YoungBear","followers_url":"https://api.github.com/users/YoungBear/followers","following_url":"https://api.github.com/users/YoungBear/following{/other_user}","gists_url":"https://api.github.com/users/YoungBear/gists{/gist_id}","starred_url":"https://api.github.com/users/YoungBear/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/YoungBear/subscriptions","organizations_url":"https://api.github.com/users/YoungBear/orgs","repos_url":"https://api.github.com/users/YoungBear/repos","events_url":"https://api.github.com/users/YoungBear/events{/privacy}","received_events_url":"https://api.github.com/users/YoungBear/received_events","type":"User","site_admin":false,"score":7.678602},{"login":"yqsb","id":27816039,"avatar_url":"https://avatars3.githubusercontent.com/u/27816039?v=4","gravatar_id":"","url":"https://api.github.com/users/yqsb","html_url":"https://github.com/yqsb","followers_url":"https://api.github.com/users/yqsb/followers","following_url":"https://api.github.com/users/yqsb/following{/other_user}","gists_url":"https://api.github.com/users/yqsb/gists{/gist_id}","starred_url":"https://api.github.com/users/yqsb/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/yqsb/subscriptions","organizations_url":"https://api.github.com/users/yqsb/orgs","repos_url":"https://api.github.com/users/yqsb/repos","events_url":"https://api.github.com/users/yqsb/events{/privacy}","received_events_url":"https://api.github.com/users/yqsb/received_events","type":"User","site_admin":false,"score":5.173872},{"login":"Youngever","id":11658533,"avatar_url":"https://avatars3.githubusercontent.com/u/11658533?v=4","gravatar_id":"","url":"https://api.github.com/users/Youngever","html_url":"https://github.com/Youngever","followers_url":"https://api.github.com/users/Youngever/followers","following_url":"https://api.github.com/users/Youngever/following{/other_user}","gists_url":"https://api.github.com/users/Youngever/gists{/gist_id}","starred_url":"https://api.github.com/users/Youngever/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/Youngever/subscriptions","organizations_url":"https://api.github.com/users/Youngever/orgs","repos_url":"https://api.github.com/users/Youngever/repos","events_url":"https://api.github.com/users/Youngever/events{/privacy}","received_events_url":"https://api.github.com/users/Youngever/received_events","type":"User","site_admin":false,"score":4.38091},{"login":"flying-bs","id":31202201,"avatar_url":"https://avatars1.githubusercontent.com/u/31202201?v=4","gravatar_id":"","url":"https://api.github.com/users/flying-bs","html_url":"https://github.com/flying-bs","followers_url":"https://api.github.com/users/flying-bs/followers","following_url":"https://api.github.com/users/flying-bs/following{/other_user}","gists_url":"https://api.github.com/users/flying-bs/gists{/gist_id}","starred_url":"https://api.github.com/users/flying-bs/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/flying-bs/subscriptions","organizations_url":"https://api.github.com/users/flying-bs/orgs","repos_url":"https://api.github.com/users/flying-bs/repos","events_url":"https://api.github.com/users/flying-bs/events{/privacy}","received_events_url":"https://api.github.com/users/flying-bs/received_events","type":"User","site_admin":false,"score":1.7350022}]
     */

    private int total_count;
    private boolean incomplete_results;
    private List<ItemsBean> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * login : cng1985
         * id : 302495
         * avatar_url : https://avatars2.githubusercontent.com/u/302495?v=4
         * gravatar_id :
         * url : https://api.github.com/users/cng1985
         * html_url : https://github.com/cng1985
         * followers_url : https://api.github.com/users/cng1985/followers
         * following_url : https://api.github.com/users/cng1985/following{/other_user}
         * gists_url : https://api.github.com/users/cng1985/gists{/gist_id}
         * starred_url : https://api.github.com/users/cng1985/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/cng1985/subscriptions
         * organizations_url : https://api.github.com/users/cng1985/orgs
         * repos_url : https://api.github.com/users/cng1985/repos
         * events_url : https://api.github.com/users/cng1985/events{/privacy}
         * received_events_url : https://api.github.com/users/cng1985/received_events
         * type : User
         * site_admin : false
         * score : 15.201826
         */

        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;
        private double score;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}
