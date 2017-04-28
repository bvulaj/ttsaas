Make a rest call to a text to speech service to say something outloud from the host running the plugin

#install supybot
see: https://github.com/Supybot/Supybot/blob/master/INSTALL
> git clone https://github.com/Supybot/Supybot.git
> python setup.py install

#configure supybot
> supybot-wizard
follow prompts...

#run supybot
> supybot conf_file_created_in_supybot-wizard.conf

#install Say plugin
> pip install requests
> ln -s $THIS_REPO_DIR/supybot_plugin/Say $SUPYBOT_PLUGIN_DIR/Say

#load Say Plugin
in IRC Supybot is connected to:
> /msg supybot identify owner password
> supybot Owner load Say
> supybot say hello world!
