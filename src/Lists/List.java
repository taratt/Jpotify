package Lists;
import Music.Song;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

    public class List {
        protected ArrayList<String> songPath;
        protected String name;
        protected String description;
        protected byte[] artwork;
        public List(String name,String description,byte[] artwork)
        {
            this.name=name;
            this.description=description;
            this.artwork=artwork;
            songPath=new ArrayList<>();
        }

        public String getName()
        {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public byte[] getArtwork() {
            return artwork;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setArtwork(byte[] artwork) {
            this.artwork = artwork;
        }

        public ArrayList<String> getsongPath() {
            return songPath;
        }
        public ArrayList<String> search(String key){
            Song song;
            ArrayList<String> foundItems=new ArrayList<>();
            for (String path: songPath) {
                try {
                    song=new Song(path);
                    if(song.getTitle().contains(key))
                        foundItems.add(path);
                    if(song.getAlbum().contains(key))
                        foundItems.add(path);
                    if(song.getArtist().contains(key))
                        foundItems.add(path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
            return foundItems;
        }
        public ArrayList<String> shuffle(){
            ArrayList<String> shuffledsongPath=new ArrayList<>();
            for (int i = 0; i < songPath.size(); i++) {
                shuffledsongPath.add(songPath.get(i));
            }
            Collections.shuffle(shuffledsongPath);
            return shuffledsongPath;
        }
        public String nextSong(String path){
            int index= songPath.indexOf(path);
            if(index!=songPath.size()-1)
                return songPath.get(index+1);
            else
                return songPath.get(0);

        }
        public String previousSong(String path){
            int index=songPath.indexOf(path);
            if(index!=0)
                return songPath.get(index-1);
            else
                return songPath.get(songPath.size()-1);
        }

    public ArrayList<String> getSongPath() {
        return songPath;
    }
}
