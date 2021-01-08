package ru.nsu.localove.core.nearbyusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nsu.localove.R

class ProfileCardAdapter(
        private val dataSet: List<ProfileCardWithPhoto>,
        private val onClick: (ProfileCardWithPhoto) -> Unit
) : RecyclerView.Adapter<ProfileCardAdapter.ProfileCardHolder>() {

    class ProfileCardHolder(
            view: View,
            val onClick: (ProfileCardWithPhoto) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private var currentProfileCard: ProfileCardWithPhoto? = null

        init {
            view.setOnClickListener {
                currentProfileCard?.let {
                    onClick(it)
                }
            }
        }

        fun bind(profileCard: ProfileCardWithPhoto) {
            currentProfileCard = profileCard
            // TODO: туду
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCardHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_card_item, parent, false)
        return ProfileCardHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProfileCardHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}