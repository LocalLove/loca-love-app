package ru.nsu.localove.core.nearbyusers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.localove.api.user.ProfileCard
import ru.nsu.localove.R

class ProfileCardAdapter(
    private val onClick: (ProfileCard) -> Unit
) : RecyclerView.Adapter<ProfileCardAdapter.ProfileCardHolder>() {

    var dataSet: List<ProfileCard> = listOf()
    var idToAvatarBytesMap: Map<Long, ByteArray> = mapOf()

    class ProfileCardHolder(
            view: View,
            val onClick: (ProfileCard) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private var currentProfileCard: ProfileCard? = null
        private val cardTitleView: TextView = view.findViewById(R.id.cardTitle)
        private val cardStatusView: TextView = view.findViewById(R.id.cardStatus)
        private val cardPhoto: ImageView = view.findViewById(R.id.cardPhoto)

        init {
            view.setOnClickListener {
                currentProfileCard?.let {
                    onClick(it)
                }
            }
        }

        fun bind(profileCard: ProfileCard, cardBytes: ByteArray?) {
            currentProfileCard = profileCard
            cardTitleView.text = "${profileCard.name}, ${profileCard.age}"
            cardStatusView.text = profileCard.status
            if (cardBytes != null) {
                val bitmap = BitmapFactory.decodeByteArray(cardBytes, 0, cardBytes.size)
                cardPhoto.setImageBitmap(bitmap)
            } else {
                cardPhoto.setImageResource(R.drawable.ic_profile_24dp)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCardHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_card_item, parent, false)
        return ProfileCardHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProfileCardHolder, position: Int) {
        holder.bind(dataSet[position], idToAvatarBytesMap[dataSet[position].id])
    }

    override fun getItemCount() = dataSet.size
}