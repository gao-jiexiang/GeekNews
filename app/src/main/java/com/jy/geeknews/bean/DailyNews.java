package com.jy.geeknews.bean;

import java.util.List;

public class DailyNews {
    /**
     * date : 20200511
     * stories : [{"image_hue":"0x342e42","title":"为什么把建筑设计得具象化是一种很 low 的行为？","url":"https://daily.zhihu.com/story/9723673","hint":"陈芒果 · 3 分钟阅读","ga_prefix":"051107","images":["https://pic1.zhimg.com/v2-c7f7a6a357220d3bd3165a91c2f1d5c8.jpg"],"type":0,"id":9723673},{"image_hue":"0x727272","title":"中国各地的地铁站命名都有哪些特点? ","url":"https://daily.zhihu.com/story/9723683","hint":"不才不争 · 4 分钟阅读","ga_prefix":"051107","images":["https://pic3.zhimg.com/v2-685313e927e05a1d5401f1d0631e5442.jpg"],"multipic":true,"type":0,"id":9723683},{"image_hue":"0xb37d7d","title":"跳槽时多大的涨薪幅度较为合理？","url":"https://daily.zhihu.com/story/9723687","hint":"索小姐 · 3 分钟阅读","ga_prefix":"051107","images":["https://pic1.zhimg.com/v2-4a584887ff1f76c11ab713f6a7c6e7dc.jpg"],"type":0,"id":9723687},{"image_hue":"0x231b11","title":"解谜类游戏有哪些经典谜题？","url":"https://daily.zhihu.com/story/9723659","hint":"知乎用户 · 4 分钟阅读","ga_prefix":"051107","images":["https://pic1.zhimg.com/v2-64cccb51f862b8b72555e8c88f74b440.jpg"],"multipic":true,"type":0,"id":9723659},{"image_hue":"0x291f1d","title":"如何死后成为一块优秀的化石？","url":"https://daily.zhihu.com/story/9723700","hint":"090811mario · 5 分钟阅读","ga_prefix":"051107","images":["https://pic1.zhimg.com/v2-a8ccc9976a802d474bd01e1ddd642aa0.jpg"],"multipic":true,"type":0,"id":9723700},{"image_hue":"0x807132","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9723619","hint":"VOL.2394","ga_prefix":"051106","images":["https://pic1.zhimg.com/v2-062525e76c9c2e6a015c14bd71014bd0.jpg"],"type":0,"id":9723619}]
     * top_stories : [{"image_hue":"0xb38562","hint":"作者 / 赵浪","url":"https://daily.zhihu.com/story/9723588","image":"https://pic1.zhimg.com/v2-898477115820448df42ae4ab4ccc1c1c.jpg","title":"用勺子挖掉一块脑组织会失去意识吗？","ga_prefix":"050907","type":0,"id":9723588},{"image_hue":"0x4a3f31","hint":"作者 / 庄有猫","url":"https://daily.zhihu.com/story/9723639","image":"https://pic3.zhimg.com/v2-aa44df641cb66e95a481a485e403ca82.jpg","title":"新冠病毒会不会像非典一样突然消失呢？","ga_prefix":"051007","type":0,"id":9723639},{"image_hue":"0x8f6464","hint":"作者 / 陈一丁","url":"https://daily.zhihu.com/story/9723548","image":"https://pic4.zhimg.com/v2-2ce69e40b98267733602aa2cde959887.jpg","title":"为什么感觉现在的国产电视剧越来越不堪入目？","ga_prefix":"050807","type":0,"id":9723548},{"image_hue":"0xb27a93","hint":"作者 / 司马懿","url":"https://daily.zhihu.com/story/9723515","image":"https://pic1.zhimg.com/v2-2321c7c234293a8c692d9c737a8c72c8.jpg","title":"如何从经济学的角度评价「分手后无缝衔接」这种行为？","ga_prefix":"050707","type":0,"id":9723515},{"image_hue":"0x0a39b0","hint":"作者 / Sean Ye","url":"https://daily.zhihu.com/story/9723489","image":"https://pic1.zhimg.com/v2-e9886ea020b36f655ba7bccc40b3ee88.jpg","title":"为什么职位越高的人越不排斥上班，特别是有些领导是加班控？","ga_prefix":"050607","type":0,"id":9723489}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * image_hue : 0x342e42
         * title : 为什么把建筑设计得具象化是一种很 low 的行为？
         * url : https://daily.zhihu.com/story/9723673
         * hint : 陈芒果 · 3 分钟阅读
         * ga_prefix : 051107
         * images : ["https://pic1.zhimg.com/v2-c7f7a6a357220d3bd3165a91c2f1d5c8.jpg"]
         * type : 0
         * id : 9723673
         * multipic : true
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private int id;
        private boolean multipic;
        private List<String> images;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image_hue : 0xb38562
         * hint : 作者 / 赵浪
         * url : https://daily.zhihu.com/story/9723588
         * image : https://pic1.zhimg.com/v2-898477115820448df42ae4ab4ccc1c1c.jpg
         * title : 用勺子挖掉一块脑组织会失去意识吗？
         * ga_prefix : 050907
         * type : 0
         * id : 9723588
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private int id;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
