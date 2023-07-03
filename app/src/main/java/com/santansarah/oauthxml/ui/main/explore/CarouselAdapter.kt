package com.santansarah.oauthxml.ui.main.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santansarah.oauthxml.data.remote.models.UserRepo
import com.santansarah.oauthxml.databinding.RepoCardBinding

class CarouselAdapter(private val userUserRepos: List<UserRepo>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(RepoCardBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            .root)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        RepoCardBinding.bind(viewHolder.itemView).apply{
            tvRepoName.text = userUserRepos[position].name
            tvRepoFullName.text = userUserRepos[position].fullName
            tvDescription.text = userUserRepos[position].description
        }

    }

    override fun getItemCount() = userUserRepos.count()

}
