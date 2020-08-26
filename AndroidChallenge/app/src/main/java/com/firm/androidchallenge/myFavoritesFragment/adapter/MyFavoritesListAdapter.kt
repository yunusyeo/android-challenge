package com.firm.androidchallenge.myFavoritesFragment.adapter

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.firm.androidchallenge.R
import com.firm.androidchallenge.databinding.BindingItemMyFavorites
import com.firm.androidchallenge.model.Sound
import com.firm.androidchallenge.myFavoritesFragment.DeleteFavorite
import java.io.IOException


class MyFavoritesListAdapter(val listener: DeleteFavorite) :
    ListAdapter<Sound, MyFavoritesListAdapter.MyViewHolder>(DiffCallback()) {
    //Çalan sesleri HashMap te tutuyorum. Çünkü her item a ait media player var.
    lateinit var mediaPlayer: MediaPlayer
    var mediaPlayerList = HashMap<Int, MediaPlayer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_my_favorites,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sound = getItem(position)
        holder.binding.sound = sound
        holder.binding.executePendingBindings()
        holder.binding.deleteFavoriteBtn.setOnClickListener {
            listener.onItemClick(sound)
        }
        holder.binding.soundRunBtn.setOnClickListener {
            //HashMap media player kontrolü
            if (!mediaPlayerList.isNullOrEmpty() && mediaPlayerList.get(position) != null) {
                mediaPlayer = mediaPlayerList.get(position)!!
            } else {
                mpRun(sound.soundUrl, holder)
            }
            if (mediaPlayer.isPlaying) {
                holder.binding.soundRunBtn.setImageResource(R.drawable.ic_voice_play)
                mediaPlayer.pause()
            } else {
                holder.binding.soundRunBtn.setImageResource(R.drawable.ic_voice_stop)
                mediaPlayer.start()
                mediaPlayerList.put(position, mediaPlayer)
            }
        }

        holder.binding.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if (mediaPlayer.isPlaying) {
                    val volume = progress / 100f
                    mediaPlayer.setVolume(volume, volume)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun mpRun(soundUrl: String?, holder: MyViewHolder) {
        try {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                } else {
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                }
                seekTo(0)
                setVolume(0.5f, 0.5f)
                setDataSource(soundUrl)
                prepare()
            }

        } catch (e: Exception) {
        } catch (e: IllegalArgumentException) {
        } catch (e: IOException) {
        }
    }

    class MyViewHolder(val binding: BindingItemMyFavorites) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Sound>() {
        override fun areItemsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sound, newItem: Sound): Boolean {
            return oldItem == newItem
        }

    }


}