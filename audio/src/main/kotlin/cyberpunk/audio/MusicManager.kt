package cyberpunk.audio

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

object MusicManager {

  /**
   * Data structure that will hold all [Music] instances.
   * Note, however, as stated in the official LibGDX docs, that [Music] instances
   * are heavy, and you should not have more than one or two loaded at most.
   */
  private val tracks: MutableMap<String, Music> = mutableMapOf()

  /**
   * Creates and loads a [Music] object onto the [tracks] structure.
   * @param trackName key that will identify this track in the map.
   * @param path      track's path inside the assets folder.
   */
  @JvmStatic
  fun load(trackName: String, path: String) {
    val track = Gdx.audio.newMusic(Gdx.files.internal(path))
    tracks.put(trackName, track)
  }

  /**
   * Plays the given track, if present in the [tracks] map.
   * If no volume is specified (the second parameter), then the sound will be played with
   * the default volume, 1f.
   * Contrarily to the sound instances, this action doesn't return an unique id.
   * @param trackName key that identifies the track in the map.
   * @param volume    audio volume; ranges between [0, 1] and it's optional.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @JvmOverloads
  @Throws(AudioAssetNotFoundException::class)
  fun play(trackName: String, volume: Float = 1f) {
    val track = tracks[trackName] ?: throw AudioAssetNotFoundException("$trackName not found.")
    track.volume = volume
    track.play()
  }

  /**
   * Pauses the given track's playback.
   * @param trackName key that identifies the track in the map.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun pause(trackName: String) =
    tracks[trackName]?.pause() ?: throw AudioAssetNotFoundException("$trackName not found.")

  /**
   * Stops the given track's playback.
   * @param trackName key that identifies the track in the map.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun stop(trackName: String) =
    tracks[trackName]?.stop() ?: throw AudioAssetNotFoundException("$trackName not found.")

  /**
   * Repeats the given track's playback until [stop] is called.
   * @param trackName key that identifies the track in the map.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun loop(trackName: String) {
    val track = tracks[trackName] ?: throw AudioAssetNotFoundException("$trackName not found.")
    track.isLooping = true
  }

  /**
   * Checks whether or not the given track is playing.
   * @param trackName key that identifies the track in the map.
   * @return whether or not the given track is playing.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun isPlaying(trackName: String): Boolean =
    tracks[trackName]?.isPlaying ?: throw AudioAssetNotFoundException("$trackName not found.")

  /**
   * Checks whether or not the given track is looping.
   * @param trackName key that identifies the track in the map.
   * @return whether or not the given track is looping.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun isLooping(trackName: String): Boolean =
    tracks[trackName]?.isLooping ?: throw AudioAssetNotFoundException("$trackName not found.")

  /**
   * Gets this track's current position in seconds.
   * @param trackName key that identifies the track in the map.
   * @return this track's current position in seconds.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun getPosition(trackName: String): Float =
    tracks[trackName]?.position ?: throw AudioAssetNotFoundException("$trackName not found.")

  /**
   * Sets this track's current position in seconds.
   * @param trackName key that identifies the track in the map.
   * @param position  target position in seconds.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun setPosition(trackName: String, position: Float) {
    val track = tracks[trackName] ?: throw AudioAssetNotFoundException("$trackName not found.")
    track.position = position
  }

  /**
   * Releases resources held by the [Music] instances.
   * Accessing the sound after you disposed of it will result in undefined errors.
   * @param trackName key that identifies the track in the map.
   * @throws AudioAssetNotFoundException
   */
  @JvmStatic
  @Throws(AudioAssetNotFoundException::class)
  fun dispose(trackName: String) =
    tracks[trackName]?.dispose() ?: throw AudioAssetNotFoundException("$trackName not found.")
}
